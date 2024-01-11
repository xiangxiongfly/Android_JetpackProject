package com.example.cameraxdemo

import android.content.res.Resources
import android.util.Log
import android.widget.Toast
import com.example.cameraxdemo.base.BaseApplication

fun showToast(text: String) {
    Toast.makeText(BaseApplication.mContext, text, Toast.LENGTH_SHORT).show()
}

fun logE(msg: String) {
    Log.e("CameraX", msg)
}

fun dp2px(dp: Int): Int {
    return (dp * Resources.getSystem().displayMetrics.density + 0.5f).toInt()
}