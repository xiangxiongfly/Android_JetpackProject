package com.example.viewbindingdemo.base3

import android.os.Bundle
import android.widget.Toast
import com.example.common.base.BaseActivity
import com.example.viewbindingdemo.R
import com.example.viewbindingdemo.databinding.ActivityThreeBinding


class ThreeActivity : BaseActivity() {
    private val viewBinding: ActivityThreeBinding by viewBindings(ActivityThreeBinding::inflate)
//    private val viewBinding: ActivityThreeBinding by viewBindings()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding.ivAvatar.setImageResource(R.mipmap.ic_launcher_round)
        viewBinding.tvName.text = "小白3"
        viewBinding.tvAge.text = 38.toString()
        viewBinding.btn.setOnClickListener {
            Toast.makeText(mContext, "hello3", Toast.LENGTH_SHORT).show()
        }
    }
}