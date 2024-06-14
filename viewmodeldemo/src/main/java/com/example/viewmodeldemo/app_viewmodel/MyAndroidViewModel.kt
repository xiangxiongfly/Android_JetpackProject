package com.example.viewmodeldemo.app_viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class MyAndroidViewModel(application: Application) : AndroidViewModel(application) {
    val appContext = application.applicationContext
    private val _countLiveData = MutableLiveData<Int>(0)
    val countLiveData = _countLiveData
    private var count = 0

    fun increase() {
        Log.e("TAG", "context:${appContext}")
        _countLiveData.postValue(++count)
    }

    fun decrease() {
        _countLiveData.postValue(--count)
    }

    override fun onCleared() {
        super.onCleared()
        Log.e("TAG", "onCleared")
    }
}