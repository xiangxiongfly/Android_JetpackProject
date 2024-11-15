package com.example.viewbindingdemo

import android.content.Intent
import android.view.View
import com.example.common.base.BaseActivity
import com.example.viewbindingdemo.dialog.TipDialog
import com.example.viewbindingdemo.encapsulation.base.OneActivity
import com.example.viewbindingdemo.encapsulation.delegate.ThreeActivity
import com.example.viewbindingdemo.encapsulation.reflect.TwoActivity
import com.example.viewbindingdemo.include.VBIncludeActivity
import com.example.viewbindingdemo.rv.RvActivity
import com.example.viewbindingdemo.simple.SimpleActivity
import com.example.viewbindingdemo.simple.SimpleFragmentActivity

class MainActivity : BaseActivity(R.layout.activity_main) {

    fun toSimple(v: View) {
        startActivity(Intent(mContext, SimpleActivity::class.java))
    }

    fun toFragment(v: View) {
        startActivity(Intent(mContext, SimpleFragmentActivity::class.java))
    }

    fun toRV(v: View) {
        startActivity(Intent(mContext, RvActivity::class.java))
    }

    fun toDialog(v: View) {
        TipDialog(mContext).show()
    }

    fun toInclude(v: View) {
        VBIncludeActivity.start(this)
    }

    fun toBase1(v: View) {
        startActivity(Intent(mContext, OneActivity::class.java))
    }

    fun toBase2(v: View) {
        startActivity(Intent(mContext, TwoActivity::class.java))
    }

    fun toBase3(v: View) {
        startActivity(Intent(mContext, ThreeActivity::class.java))
    }
}