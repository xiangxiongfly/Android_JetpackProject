package com.example.base

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.base.action.HandlerAction

open class BaseActivity : AppCompatActivity(), HandlerAction {
    protected lateinit var mContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
    }

    override fun onDestroy() {
        super.onDestroy()
        removeCallbacks()
    }
}
