package com.example.appstartupdemo

import android.content.Context
import androidx.startup.Initializer
import org.litepal.LitePal

class AppInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        LitePal.initialize(context)
        /*
        一系列初始化操作。。。
         */
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }
}