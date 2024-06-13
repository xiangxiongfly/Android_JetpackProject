package com.example.lifecyclerdemo.advertising3

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.example.lifecyclerdemo.R
import com.example.lifecyclerdemo.SecondActivity
import com.example.lifecyclerdemo.advertising2.AdvertisingManager2

class LifecycleCustomActivity : Activity(), LifecycleOwner {
    private lateinit var textView: TextView
    private lateinit var btnNext: Button

    private lateinit var lifecycleRegistry: LifecycleRegistry
    private var advertisingManager: AdvertisingManager2? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_advertising)
        // 创建对象
        lifecycleRegistry = LifecycleRegistry(this)
        // 分发onCreate事件
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
        val context = this
        textView = findViewById(R.id.text_view)
        btnNext = findViewById(R.id.btn_next)
        btnNext.setOnClickListener {
            startActivity(Intent(context, SecondActivity::class.java))
            finish()
        }

        advertisingManager = AdvertisingManager2()
        // 添加观察者
        lifecycle.addObserver(advertisingManager!!)
        advertisingManager!!.setOnAdvertisingListener(object :
            AdvertisingManager2.OnAdvertisingListener {
            override fun time(second: Int) {
                textView.text = "广告剩余时间：${second}秒"
            }

            override fun jumpToActivity() {
                startActivity(Intent(context, SecondActivity::class.java))
                finish()
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        // 分发onDestroy事件
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        advertisingManager = null
    }

    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }
}