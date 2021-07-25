package com.example.hiltdemo.entity

import com.example.hiltdemo.di.BindElectricEngine
import com.example.hiltdemo.di.BindGasEngine
import com.example.hiltdemo.log
import javax.inject.Inject

class Car @Inject constructor(val user: User) {

    @Inject
    lateinit var engine: Engine

    @BindGasEngine
    @Inject
    lateinit var gasEngine: Engine

    @BindElectricEngine
    @Inject
    lateinit var electricEngine: Engine

    fun drive() {
        log("$user 正在开车")
        engine.start()
        engine.shutdown()

        gasEngine.start()
        gasEngine.shutdown()

        electricEngine.start()
        electricEngine.shutdown()
    }
}