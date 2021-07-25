package com.example.hiltdemo.entity

import com.example.hiltdemo.di.BindElectricEngine
import com.example.hiltdemo.di.BindGasEngine
import com.example.hiltdemo.log
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
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

@Module
@InstallIn(ActivityComponent::class)
abstract class EngineModule {

    @Binds
    abstract fun bindEngine(gasEngine: GasEngine): Engine
}


@Module
@InstallIn(ActivityComponent::class)
abstract class SelectEngineModule {

    @BindGasEngine
    @Binds
    abstract fun bindGasEngine(gasEngine: GasEngine): Engine

    @BindElectricEngine
    @Binds
    abstract fun bindElectricEngine(electricEngine: ElectricEngine): Engine
}