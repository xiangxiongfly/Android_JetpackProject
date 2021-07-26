package com.example.hiltdemo.di

import com.example.hiltdemo.entity.Engine
import com.example.hiltdemo.entity.GasEngine
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class EngineModule {

    @Binds
    abstract fun bindEngine(gasEngine: GasEngine): Engine
}
