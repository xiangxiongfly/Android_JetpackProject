package com.example.paging3demo.utils

import android.util.Log
import android.widget.Toast
import com.example.paging3demo.base.BaseApplication

fun showToast(message: String) {
    Toast.makeText(BaseApplication.context, message, Toast.LENGTH_SHORT).show()
}

fun logE(msg: String) {
    Log.e("Paging3", msg)
}