package com.example.myapplication.base

import android.app.Application

class BaseApp : Application() {
    companion object {
        lateinit var context: BaseApp
    }

    override fun onCreate() {
        super.onCreate()
        BaseApp.context = this
    }
}