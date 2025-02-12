package com.example.hiltdemo.di

import android.app.Application
import androidx.fragment.app.FragmentActivity
import com.example.hiltdemo.utils.log
import javax.inject.Inject

class MyActivity @Inject constructor(val activity: FragmentActivity, val application: Application) {
    fun show() {
        log("activity: $activity - application:$application")
    }
}