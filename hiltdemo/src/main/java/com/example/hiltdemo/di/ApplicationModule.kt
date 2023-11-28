package com.example.hiltdemo.di

import android.app.Application
import com.example.hiltdemo.BaseApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    fun provideBaseApplication(application: Application): BaseApplication {
        return application as BaseApplication
    }
}