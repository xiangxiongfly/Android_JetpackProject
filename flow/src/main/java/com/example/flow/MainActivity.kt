package com.example.flow

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch

const val TAG = "Flow"

fun log(msg: String) = Log.e(TAG, msg)

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<MyViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initObserve()
        //        flowAsSharedFlow()
    }

    fun flowClick(view: View) {
        flow()
    }

    fun sharedFlowClick(view: View) {
        sharedFlow()
    }

    fun stateFlowClick(view: View) {
        stateFlow()
    }

    fun initObserve() {
        lifecycleScope.launch {
            viewModel.sharedFlow.collect {
                log("SharedFlow collect:$it")
            }
        }

        lifecycleScope.launch {
            viewModel.stateFlow.collect {
                log("StateFlow collect:$it")
            }
        }
    }

    //冷流
    fun flow() {
        lifecycleScope.launch {
            kotlinx.coroutines.flow.flow<String> {
                emit("hello")
                emit("flow")
            }.collect {
                log("Flow collect: $it")
            }
        }
    }

    //热流SharedFlow
    fun sharedFlow() {
        viewModel.count1()
    }

    //Flow转SharedFlow
    fun flowAsSharedFlow() {

        lifecycleScope.launch {

            kotlinx.coroutines.flow.flow<String> {
                emit("a")
                emit("b")
                emit("c")
                delay(1000L)
                emit("d")
                emit("e")
                emit("f")
            }
                .shareIn(
                    lifecycleScope,
                    SharingStarted.WhileSubscribed()
                ).collect {
                    log("SharedFlow1 collect:$it")
                }

        }
    }

    //热流StateFlow
    fun stateFlow() {
        viewModel.count2()
    }
}