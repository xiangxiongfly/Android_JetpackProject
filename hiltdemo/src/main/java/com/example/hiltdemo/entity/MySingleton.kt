package com.example.hiltdemo.entity

import com.example.hiltdemo.utils.log
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MySingleton @Inject constructor() {
    fun test() {
        log("MySingleton: $this")
    }
}