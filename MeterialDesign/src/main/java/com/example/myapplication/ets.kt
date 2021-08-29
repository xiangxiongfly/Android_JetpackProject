package com.example.myapplication

import android.widget.Toast

fun String.showToast() {
    Toast.makeText(BaseApp.context, this, Toast.LENGTH_SHORT).show()
}