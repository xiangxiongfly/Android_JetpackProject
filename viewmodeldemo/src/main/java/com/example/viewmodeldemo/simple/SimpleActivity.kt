package com.example.viewmodeldemo.simple

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.common.base.BaseActivity
import com.example.viewmodeldemo.R

class SimpleActivity : BaseActivity() {
    private lateinit var tvOrientation: TextView
    private lateinit var tvCount: TextView

    private val viewModel: MyViewModel by viewModels()

    private val viewModel2: MyViewModel by lazy {
        ViewModelProvider(this).get(MyViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple)
        Log.e("TAG", "onCreate")

        tvOrientation = findViewById(R.id.tv_orientation)
        tvCount = findViewById(R.id.tv_count)

        tvOrientation.text =
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) "竖屏" else "横屏"
        viewModel.countLiveData.observe(this, object : Observer<Int> {
            override fun onChanged(t: Int?) {
                tvCount.text = "${t}"
            }
        })
    }

    fun onIncrease(view: View) {
        viewModel.increase()
    }

    fun onDecrease(view: View) {
        viewModel.decrease()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("TAG", "onDestroy")
    }
}