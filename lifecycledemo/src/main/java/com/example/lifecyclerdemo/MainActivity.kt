package com.example.lifecyclerdemo

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.lifecyclerdemo.advertising.AdvertisingActivity
import com.example.lifecyclerdemo.advertising2.AdvertisingActivity2
import com.example.lifecyclerdemo.advertising3.LifecycleCustomActivity
import com.example.lifecyclerdemo.dialog.DialogActivity
import com.example.lifecyclerdemo.simple.LifecycleActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onSimpleClick(view: View) {
        startActivity(Intent(this, LifecycleActivity::class.java))
    }

    fun onAd1Click(view: View) {
        startActivity(Intent(this, AdvertisingActivity::class.java))
    }

    fun onAd2Click(view: View) {
        startActivity(Intent(this, AdvertisingActivity2::class.java))
    }

    fun onAd3Click(view: View) {
        startActivity(Intent(this, LifecycleCustomActivity::class.java))
    }

    fun onDialogClick(view: View) {
        startActivity(Intent(this, DialogActivity::class.java))
    }
}