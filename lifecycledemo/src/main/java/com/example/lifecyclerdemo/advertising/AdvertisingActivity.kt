package com.example.lifecyclerdemo.advertising

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.lifecyclerdemo.R
import com.example.lifecyclerdemo.SecondActivity

class AdvertisingActivity : AppCompatActivity() {
    private lateinit var textView: TextView
    private lateinit var btnNext: Button

    private var advertisingManager: AdvertisingManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_advertising)
        val context = this
        textView = findViewById(R.id.text_view)
        btnNext = findViewById(R.id.btn_next)
        btnNext.setOnClickListener {
            startActivity(Intent(context, SecondActivity::class.java))
            finish()
        }

        advertisingManager = AdvertisingManager()
        advertisingManager!!.setOnAdvertisingListener(object :
            AdvertisingManager.OnAdvertisingListener {
            override fun time(second: Int) {
                textView.text = "广告剩余时间：${second}秒"
            }

            override fun jumpToActivity() {
                startActivity(Intent(context, SecondActivity::class.java))
                finish()
            }
        })
        advertisingManager!!.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        advertisingManager!!.cancel()
        advertisingManager = null
    }
}