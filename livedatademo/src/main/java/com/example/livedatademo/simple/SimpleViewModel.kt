package com.example.livedatademo.simple

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SimpleViewModel : ViewModel() {
    private val _liveData by lazy { MutableLiveData<String>() }
    val liveData: LiveData<String> = _liveData

    fun sendMessageInUI(message: String) {
        _liveData.setValue(message)
    }

    fun sendMessage(message: String) {
        _liveData.postValue(message)
    }
}