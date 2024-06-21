package com.example.livedatademo.other

import android.util.Log
import androidx.lifecycle.LiveData

class StockLiveData : LiveData<String>() {
    private val stockManager = StockManager()

    companion object {
        private var instance: StockLiveData? = null
        fun get(): StockLiveData {
            if (instance == null) {
                instance = StockLiveData()
            }
            return instance!!
        }
    }

    private val listener = object : UpdateListener {
        override fun onUpdate(time: String) {
            postValue(time)
        }
    }

    override fun onActive() {
        super.onActive()
        Log.e("TAG", "onActive")
        stockManager.registerUpdates(listener)
        stockManager.start()
    }

    override fun onInactive() {
        super.onInactive()
        Log.e("TAG", "onInactive")
        stockManager.unregisterUpdates(listener)
        stockManager.stop()
    }
}