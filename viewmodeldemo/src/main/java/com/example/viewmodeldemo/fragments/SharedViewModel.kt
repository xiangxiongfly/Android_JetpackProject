package com.example.viewmodeldemo.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private val _liveData = MutableLiveData<String>()
    val liveData = _liveData

    fun setData(item: String) {
        _liveData.postValue("$item detail")
    }
}