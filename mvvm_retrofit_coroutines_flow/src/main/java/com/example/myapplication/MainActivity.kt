package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.entity.DataResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewModel by viewModels<MyViewModel>()

    private lateinit var btn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val textView = findViewById<TextView>(R.id.textView)
        btn = findViewById<Button>(R.id.btn)

        lifecycleScope.launch {
            viewModel.searchFlow.collect {
                when (it) {
                    is DataResult.Success -> {
                        textView.text = "$it"
                    }
                    is DataResult.Error -> {
                        toast(it.exception.msg)
                    }
                }
            }
        }

        lifecycleScope.launch {
            viewModel.bannerFlow.collect {
                when (it) {
                    is DataResult.Start -> {
                        Log.e("Hello", "开始加载")
                        textView.text = "开始加载"
                    }
                    is DataResult.Success -> {
                        Log.e("Hello", "获取数据：$it")
                        textView.text = "$it"
                    }
                    is DataResult.Error -> {
                        Log.e("Hello", "加载失败")
                        toast(it.exception.msg)
                    }
                    is DataResult.Completion -> {
                        Log.e("Hello", "加载完成")
                        textView.text = "加载完成"
                    }
                }
            }
        }

        lifecycleScope.launch {
            viewModel.bannerFlow2.collect {
                when (it) {
                    is DataResult.Start -> {
                        Log.e("Hello", "开始加载")
                        textView.text = "开始加载"
                    }
                    is DataResult.Success -> {
                        Log.e("Hello", "获取数据：$it")
                        textView.text = "$it"
                    }
                    is DataResult.Error -> {
                        Log.e("Hello", "加载失败")
                        toast(it.exception.msg)
                    }
                    is DataResult.Completion -> {
                        Log.e("Hello", "加载完成")
                        textView.text = "加载完成"
                    }
                }
            }
        }
    }

    fun onClick(v: View) {
        viewModel.search()
    }

    fun onClick2(v: View) {
        viewModel.getBanner()
    }

    fun onClick3(v: View) {
        viewModel.getBanner2()
    }
}
