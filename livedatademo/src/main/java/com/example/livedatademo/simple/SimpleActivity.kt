package com.example.livedatademo.simple

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.common.base.BaseActivity
import com.example.livedatademo.R
import kotlin.concurrent.thread

class SimpleActivity : BaseActivity() {
    private val viewModel by viewModels<SimpleViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple)
        val tvMessage = findViewById<TextView>(R.id.tv_message)
        val btnMsg = findViewById<TextView>(R.id.btn_msg)
        val btnMsgUi = findViewById<TextView>(R.id.btn_msg_ui)

        btnMsg.setOnClickListener {
            thread {
                viewModel.sendMessage("子线程消息")
            }
        }
        btnMsgUi.setOnClickListener {
            viewModel.sendMessageInUI("主线程消息")
        }

        viewModel.liveData.observe(this, object : Observer<String> {
            override fun onChanged(t: String) {
                Log.e("TAG", "onChanged:${t}")
                tvMessage.text = t
            }
        })
        viewModel.sendMessageInUI("onCreate")
    }

    override fun onStart() {
        super.onStart()
        viewModel.sendMessageInUI("onStart")
    }

    override fun onResume() {
        super.onResume()
        viewModel.sendMessageInUI("onResume")
    }

    override fun onRestart() {
        super.onRestart()
        viewModel.sendMessageInUI("onRestart")
    }

    override fun onPause() {
        super.onPause()
        viewModel.sendMessageInUI("onPause")
    }

    override fun onStop() {
        super.onStop()
        viewModel.sendMessageInUI("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.sendMessageInUI("onDestroy")
    }
}