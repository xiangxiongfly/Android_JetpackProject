package com.example.viewbindingdemo

import android.app.Application

class BaseApp : Application() {

    companion object {
        lateinit var instance: BaseApp
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}