package com.example.lifecyclerdemo

import android.app.Application
import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner

class BaseApp : Application() {
    override fun onCreate() {
        super.onCreate()
        ProcessLifecycleOwner.get().lifecycle
            .addObserver(ApplicationLifecycleObserver())
    }
}

class ApplicationLifecycleObserver : DefaultLifecycleObserver {

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        onAppForeground()
    }

    private fun onAppForeground() {
        Log.e("TAG", "当前在前台")
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        onAppBackground()
    }

    private fun onAppBackground() {
        Log.e("TAG", "当前在后台")
    }
}