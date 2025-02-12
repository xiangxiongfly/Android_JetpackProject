package com.example.hiltdemo.entity

import com.example.hiltdemo.utils.log
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class MyActivityScope @Inject constructor() {
    fun test() {
        log("MyActivityScope: $this")
    }
}