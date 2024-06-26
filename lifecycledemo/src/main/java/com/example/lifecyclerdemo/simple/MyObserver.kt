package com.example.lifecyclerdemo.simple

import android.util.Log

class MyObserver : IObserver {
    override fun dispatchCreate() {
        Log.e("TAG", "MyObserver Activity#onCreate()")
    }

    override fun dispatchStart() {
        Log.e("TAG", "MyObserver Activity#onStart()")
    }

    override fun dispatchResume() {
        Log.e("TAG", "MyObserver Activity#onResume()")
    }

    override fun dispatchPause() {
        Log.e("TAG", "MyObserver Activity#onPause()")
    }

    override fun dispatchStop() {
        Log.e("TAG", "MyObserver Activity#onStop()")
    }

    override fun dispatchDestroy() {
        Log.e("TAG", "MyObserver Activity#onDestroy()")
    }
}