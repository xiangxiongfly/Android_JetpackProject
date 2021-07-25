package com.example.hiltdemo.entity

import android.content.Context
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MyContext @Inject constructor(
    @ApplicationContext val conext: Context,
    @ActivityContext val activityContext: Context
) {
}