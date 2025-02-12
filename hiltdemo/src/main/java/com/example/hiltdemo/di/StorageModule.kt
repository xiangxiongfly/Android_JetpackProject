package com.example.hiltdemo.di

import com.example.hiltdemo.annotation.Db
import com.example.hiltdemo.annotation.File
import com.example.hiltdemo.utils.DbStorage
import com.example.hiltdemo.utils.FileStorage
import com.example.hiltdemo.utils.Storage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object StorageModule {

    @File
    @Provides
    fun privateFileStorage(): Storage {
        return FileStorage()
    }

    @Db
    @Provides
    fun privateDBStorage(): Storage {
        return DbStorage()
    }
}