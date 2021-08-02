package com.example.viewbindingdemo

import android.widget.Toast

fun toast(msg: String) = Toast.makeText(BaseApp.instance, msg, Toast.LENGTH_SHORT).show()