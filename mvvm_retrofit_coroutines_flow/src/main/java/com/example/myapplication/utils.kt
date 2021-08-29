package com.example.myapplication

import android.widget.Toast
import com.example.myapplication.base.BaseApp

fun toast(msg: String) = Toast.makeText(BaseApp.context, msg, Toast.LENGTH_SHORT).show()