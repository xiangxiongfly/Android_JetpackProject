package com.example.hiltdemo.entity

import com.example.hiltdemo.utils.log
import javax.inject.Inject

class Car @Inject constructor(private val engine: Engine) {
    fun start() {
        log("汽车启动")
        engine.start()
    }
}