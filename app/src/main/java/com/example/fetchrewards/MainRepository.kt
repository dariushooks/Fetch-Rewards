package com.example.fetchrewards

import com.example.fetchrewards.api.FetchRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val fetchRequest: FetchRequest,
) {
    fun getItems() : Flow<List<Pair<Int, List<FetchItem>>>> = flow {
        try{
            val rawItems = fetchRequest.getItems()
            val fetchMap = mutableMapOf<Int, List<FetchItem>>()

            rawItems.let { items ->
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

            emit(fetchMap.toList())
        }catch (e : Exception){
            emit(emptyList())
        }
    }
}