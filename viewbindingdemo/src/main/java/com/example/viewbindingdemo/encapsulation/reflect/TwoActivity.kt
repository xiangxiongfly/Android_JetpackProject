package com.example.viewbindingdemo.encapsulation.reflect

import android.os.Bundle
import android.widget.Toast
import com.example.viewbindingdemo.R
import com.example.viewbindingdemo.databinding.ActivityTwoBinding

class TwoActivity : BindingActivity<ActivityTwoBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewBinding.ivAvatar.setImageResource(R.mipmap.ic_launcher_round)
        mViewBinding.tvName.text = "小白"
        mViewBinding.tvAge.text = 28.toString()
        mViewBinding.btn.setOnClickListener {
            Toast.makeText(mContext, "hello2", Toast.LENGTH_SHORT).show()
        }
    }
}