package com.example.hiltdemo.di

import android.app.Application
import com.example.hiltdemo.utils.log
import javax.inject.Inject

class MyApp @Inject constructor(val application: Application) {
    fun show() {
        log("application: $application")
    }
}