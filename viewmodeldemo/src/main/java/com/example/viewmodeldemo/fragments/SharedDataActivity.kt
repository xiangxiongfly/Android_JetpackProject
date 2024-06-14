package com.example.viewmodeldemo.fragments

import android.os.Bundle
import com.example.common.base.BaseActivity
import com.example.viewmodeldemo.R

class SharedDataActivity : BaseActivity(R.layout.activity_sharred_data) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_menu, MenuFragment.newInstance())
            .replace(R.id.fl_detail, DetailFragment.newInstance())
            .commit()
    }
}