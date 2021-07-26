package com.example.hiltdemo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: Repository
) : ViewModel() {

    private val liveData = MutableLiveData<String>()

    fun loadData(): MutableLiveData<String> {
        viewModelScope.launch {
            delay(5000L)
            liveData.value = repository.getData()
        }
        return liveData
    }
}