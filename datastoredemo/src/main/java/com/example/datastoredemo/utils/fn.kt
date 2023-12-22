package com.example.datastoredemo.utils

import android.util.Log
import android.widget.Toast
import com.example.datastoredemo.base.BaseApplication

fun logE(msg: String) {
    Log.e("DataStore", msg)
}

fun showToast(message: String) {
    Toast.makeText(BaseApplication.context, message, Toast.LENGTH_SHORT).show()
}