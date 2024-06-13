package com.example.lifecyclerdemo.simple

import android.util.Log

class MyObserver : IObserver {
    override fun dispatchCreate() {
        Log.e("TAG", "Activity#onCreate()")
    }

    override fun dispatchStart() {
        Log.e("TAG", "Activity#onStart()")
    }

    override fun dispatchResume() {
        Log.e("TAG", "Activity#onResume()")
    }

    override fun dispatchPause() {
        Log.e("TAG", "Activity#onPause()")
    }

    override fun dispatchStop() {
        Log.e("TAG", "Activity#onStop()")
    }

    override fun dispatchDestroy() {
        Log.e("TAG", "Activity#onDestroy()")
    }
}