package com.example.hiltdemo.utils

import android.util.Log

fun log(message: String) {
    Log.e("Hilt", message)
}

interface Storage {
    fun save()
}

class FileStorage : Storage {
    override fun save() {
        log("使用文件存储数据")
    }
}

class DbStorage : Storage {
    override fun save() {
        log("使用数据库存储数据")
    }
}