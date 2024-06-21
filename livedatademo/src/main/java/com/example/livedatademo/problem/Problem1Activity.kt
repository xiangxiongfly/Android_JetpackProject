package com.example.livedatademo.problem

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.example.livedatademo.R

class Problem1Activity : AppCompatActivity(R.layout.activity_problem1) {

    val liveData = MutableLiveData<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        liveData.observe(this) {
            Log.e("TAG", "postValue: ${it}")
        }
    }

    fun onClick(view: View) {
        liveData.postValue("消息1")
        liveData.postValue("消息2")
        liveData.postValue("消息3")
        liveData.postValue("消息4")
    }
}