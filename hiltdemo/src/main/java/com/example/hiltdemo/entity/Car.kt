package com.example.hiltdemo.entity

import com.example.hiltdemo.di.BindElectricEngine
import com.example.hiltdemo.di.BindGasEngine
import com.example.hiltdemo.log
import javax.inject.Inject

class Car @Inject constructor(val driver: Driver) {

    @Inject
    lateinit var engine: Engine

    @BindGasEngine
    @Inject
    lateinit var gasEngine: Engine

    @BindElectricEngine
    @Inject
    lateinit var electricEngine: Engine

    fun start() {

//        engine.start()
//        log("$driver 正在开车")
//        engine.shutdown()

        gasEngine.start()
        electricEngine.start()
        log("hello $driver 正在开车")
        gasEngine.shutdown()
        electricEngine.shutdown()
    }
}