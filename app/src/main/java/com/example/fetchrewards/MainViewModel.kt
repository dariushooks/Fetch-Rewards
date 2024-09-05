package com.example.fetchrewards

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository) : ViewModel(){
    private val _fetchItems = mutableStateOf<Map<Int, List<FetchItem>>>(mapOf())
    val fetchItems : MutableState<Map<Int, List<FetchItem>>> get() = _fetchItems
    init {
        getItems()
    }

    private fun getItems(){
        viewModelScope.launch {
           _fetchItems.value = repository.getItems()
        }
    }
}