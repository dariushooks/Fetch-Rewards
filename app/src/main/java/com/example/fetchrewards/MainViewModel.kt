package com.example.fetchrewards

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository) : ViewModel(){
    val fetchItems = mutableStateOf<List<Pair<Int, List<FetchItem>>>?>(null)

    init {
        getItems()
    }

    fun getItems(){
        if(fetchItems.value != null)
            fetchItems.value = null
        viewModelScope.launch {
           repository.getItems().collect{
               fetchItems.value = it
           }
        }
    }

}