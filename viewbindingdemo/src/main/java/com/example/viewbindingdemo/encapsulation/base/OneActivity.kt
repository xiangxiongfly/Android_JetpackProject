package com.example.viewbindingdemo.encapsulation.base

import android.os.Bundle
import android.widget.Toast
import com.example.viewbindingdemo.R
import com.example.viewbindingdemo.databinding.ActivityOneBinding

class OneActivity : BindingActivity<ActivityOneBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewBinding.ivAvatar.setImageResource(R.mipmap.ic_launcher_round)
        mViewBinding.tvName.text = "小白"
        mViewBinding.tvAge.text = 18.toString()
        mViewBinding.btn.setOnClickListener {
            Toast.makeText(mContext, "hello", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getViewBinding(): ActivityOneBinding {
        return ActivityOneBinding.inflate(layoutInflater)
    }
}