package com.example.lifecyclerdemo.simple

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lifecyclerdemo.R

class LifecycleActivity : AppCompatActivity() {
    val myObserver = MyObserver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycle)
        myObserver.dispatchCreate()
        lifecycle.addObserver(MyObserver2())
        lifecycle.addObserver(MyObserver3())
    }

    override fun onStart() {
        super.onStart()
        myObserver.dispatchStart()
    }

    override fun onResume() {
        super.onResume()
        myObserver.dispatchResume()
    }

    override fun onPause() {
        super.onPause()
        myObserver.dispatchPause()
    }

    override fun onStop() {
        super.onStop()
        myObserver.dispatchStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        myObserver.dispatchDestroy()
    }
}

