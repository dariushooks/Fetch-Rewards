package com.example.fetchrewards

import com.example.fetchrewards.api.FetchRequest
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val fetchRequest: FetchRequest,
) {
    suspend fun getItems() : Map<Int, List<FetchItem>>{
        val rawItems = fetchRequest.getItems().body()
        val fetchMap = mutableMapOf<Int, List<FetchItem>>()

        rawItems?.let { items ->
            //Filter out items with names that are blank or null
            items.filter { item ->
                item.name != null && item.name != ""
            }.groupBy { it.listId }.entries.sortedBy { entry ->
                //Group the filtered items by listId
                entry.key
            }.forEach { entry ->
                //Sort the grouped items by name
                val sortedList = entry.value.sortedBy {
                    it.name?.substring(5, it.name.length)?.toInt()
                }

                fetchMap[entry.key] = sortedList
            }
        }

        return fetchMap
    }
}