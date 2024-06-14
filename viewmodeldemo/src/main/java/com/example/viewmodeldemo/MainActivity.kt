package com.example.viewmodeldemo

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.common.base.BaseActivity
import com.example.viewmodeldemo.app_viewmodel.AndroidViewModelActivity
import com.example.viewmodeldemo.shared.SharedDataActivity
import com.example.viewmodeldemo.simple.SimpleActivity
import com.example.viewmodeldemo.user.UserActivity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onSimpleClick(view: View) {
        startActivity(Intent(mContext, SimpleActivity::class.java))
    }

    fun onAndroidViewModelClick(view: View) {
        startActivity(Intent(mContext, AndroidViewModelActivity::class.java))
    }

    fun onViewModelParamClick(view: View) {
        startActivity(Intent(mContext, UserActivity::class.java))
    }

    fun onViewModelSharedClick(view: View) {
        startActivity(Intent(mContext, SharedDataActivity::class.java))
    }
}