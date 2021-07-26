package com.example.hiltdemo.entity

import com.example.hiltdemo.log
import javax.inject.Inject

interface Engine {
    fun start()
    fun shutdown()
}

class GasEngine @Inject constructor() : Engine {
    override fun start() {
        log("燃油引擎 start")
    }

    override fun shutdown() {
        log("燃油引擎 shutdown")
    }
}

class ElectricEngine @Inject constructor() : Engine {
    override fun start() {
        log("电力引擎 start")
    }

    override fun shutdown() {
        log("电力引擎 shutdown")
    }
}
