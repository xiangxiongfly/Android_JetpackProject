package com.example.lifecyclerdemo.simple

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

class MyObserver3 : DefaultLifecycleObserver {

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        Log.e("TAG", "MyObserver3 onCreate")
        Log.e("TAG", "MyObserver3 初始化")
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        Log.e("TAG", "MyObserver3 onStart")
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        Log.e("TAG", "MyObserver3 onResume")
        Log.e("TAG", "MyObserver3 建立连接")
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        Log.e("TAG", "MyObserver3 onPause")
        Log.e("TAG", "MyObserver3 断开连接")
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        Log.e("TAG", "MyObserver3 onStop")
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        Log.e("TAG", "MyObserver3 onDestroy")
        Log.e("TAG", "MyObserver3 释放资源")
    }
}