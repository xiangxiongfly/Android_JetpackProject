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

    // 返回值必须是Engine类型，表示给Engine类型的接口提供实例
    @Binds
    abstract fun bindEngine(gasEngine: GasEngine): Engine
}
