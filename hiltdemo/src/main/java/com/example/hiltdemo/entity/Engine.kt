package com.example.hiltdemo.entity

import com.example.hiltdemo.utils.log
import javax.inject.Inject

class Engine @Inject constructor() {
    fun start() {
        log("引擎启动")
    }
}