package com.example.hiltdemo.di

import com.example.hiltdemo.utils.log
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

interface ApiService {
    fun doSomeSth()
}

class ApiServiceImpl @Inject constructor() : ApiService {
    override fun doSomeSth() {
        log("处理一些事情")
    }
}

@InstallIn(SingletonComponent::class)
@Module
interface ApiServiceModule {
    @Binds
    fun bindApiService(apiServiceImpl: ApiServiceImpl): ApiService
}