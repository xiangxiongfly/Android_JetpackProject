package com.example.hiltdemo.entity

import android.content.Context
import com.example.hiltdemo.utils.log
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MyContext @Inject constructor(
    @ActivityContext val actContext: Context,
    @ApplicationContext val appContext: Context
) {
    fun show() {
        log("ActivityContext:${actContext}")
        log("ApplicationContext:${appContext}")
    }
}