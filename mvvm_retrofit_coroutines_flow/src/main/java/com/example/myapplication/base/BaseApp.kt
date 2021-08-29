package com.example.myapplication.base

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApp : Application() {
    companion object {
        lateinit var context: BaseApp
    }

    override fun onCreate() {
        super.onCreate()
        BaseApp.context = this
    }
}