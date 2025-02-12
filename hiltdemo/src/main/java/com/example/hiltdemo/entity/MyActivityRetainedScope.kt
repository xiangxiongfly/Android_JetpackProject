package com.example.hiltdemo.entity

import com.example.hiltdemo.utils.log
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class MyActivityRetainedScope @Inject constructor() {
    fun test() {
        log("MyActivityRetainedScope: $this")
    }
}