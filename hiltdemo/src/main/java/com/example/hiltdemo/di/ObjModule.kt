package com.example.hiltdemo.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class ObjModule {

//    @Singleton
//    @Provides
//    fun provideSingleton(): MySingleton {
//        log("provideSingleton")
//        return MySingleton()
//    }
//
//    @Singleton
//    @Provides
//    fun provideMyActivityScope(): MyActivityScope {
//        log("provideMyActivityScope")
//        return MyActivityScope()
//    }
//
//    @Singleton
//    @Provides
//    fun provideMyActivityRetainedScope(): MyActivityRetainedScope {
//        log("provideMyActivityRetainedScope")
//        return MyActivityRetainedScope()
//    }
}