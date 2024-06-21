package com.example.livedatademo

import android.content.Intent
import android.view.View
import com.example.common.base.BaseActivity
import com.example.livedatademo.other.OthersActivity
import com.example.livedatademo.problem.Problem1Activity
import com.example.livedatademo.problem.Problem2Activity
import com.example.livedatademo.simple.SimpleActivity

class MainActivity : BaseActivity(R.layout.activity_main) {

    fun onSimpleClick(view: View) {
        startActivity(Intent(mContext, SimpleActivity::class.java))
    }

    fun onOthersClick(view: View) {
        startActivity(Intent(mContext, OthersActivity::class.java))
    }

    fun onLoseClick(view: View) {
        startActivity(Intent(mContext, Problem1Activity::class.java))
    }

    fun onFlowbackClick(view: View) {
        startActivity(Intent(mContext, Problem2Activity::class.java))
    }
}