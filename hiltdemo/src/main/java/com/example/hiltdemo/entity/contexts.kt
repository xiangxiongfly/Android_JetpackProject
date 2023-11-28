package com.example.hiltdemo.entity

import android.app.Activity
import android.app.Application
import android.content.Context
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MyContext @Inject constructor(
    @ApplicationContext val appContext: Context,
    @ActivityContext val activityContext: Context
)

class MyAppAndActivity @Inject constructor(
    val application: Application,
    val activity: Activity
)
