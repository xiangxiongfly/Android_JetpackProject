package com.example.viewbindingdemo.simple

import android.os.Bundle
import android.widget.Toast
import com.example.common.base.BaseActivity
import com.example.viewbindingdemo.R
import com.example.viewbindingdemo.databinding.ActivitySimpleBinding

class SimpleActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewBinding = ActivitySimpleBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.tvName.text = "hello world"
        viewBinding.tvAge.text = 18.toString()
        viewBinding.ivAvatar.setImageResource(R.drawable.ic_launcher_background)
        viewBinding.btn.setOnClickListener {
            Toast.makeText(mContext, "hello", Toast.LENGTH_SHORT).show()
        }
    }
}