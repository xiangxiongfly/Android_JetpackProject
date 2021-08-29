package com.example.flow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {
    var count = 0
    private val _sharedFlow = MutableSharedFlow<Int>()
    val sharedFlow = _sharedFlow.asSharedFlow()

    private val _stateFlow = MutableStateFlow<Int>(count)
    val stateFlow = _stateFlow.asStateFlow()

    fun count1() {
        viewModelScope.launch {
            _sharedFlow.emit(count++)
        }
    }

    fun count2() {
        viewModelScope.launch {
            _stateFlow.value = count++
        }
    }


}