package com.example.livedatademo.problem

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.example.livedatademo.R

class Problem2Activity : AppCompatActivity(R.layout.activity_problem1) {

    val liveData = MutableLiveData<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        liveData.setValue("消息")
        liveData.observe(this) {
            Log.e("TAG", "setValue: ${it}")
        }
    }

    fun onClick(view: View) {
        liveData.setValue("消息2")
    }
}