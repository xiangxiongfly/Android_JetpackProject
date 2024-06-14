package com.example.common.base

import android.content.Context
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.example.base.action.HandlerAction


open class BaseActivity @JvmOverloads constructor(@LayoutRes contentLayoutId: Int = 0) :
    AppCompatActivity(contentLayoutId), HandlerAction {

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
