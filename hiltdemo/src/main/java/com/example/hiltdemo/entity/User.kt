package com.example.hiltdemo.entity

import com.example.hiltdemo.log
import javax.inject.Inject

//@Singleton
//@ActivityScoped
class User @Inject constructor() {
    fun sayHello() {
        log("hello")
    }
}