package com.example.viewbindingdemo.encapsulation.reflect

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.viewbindingdemo.R
import com.example.viewbindingdemo.databinding.FragmentTwoBinding

class TwoFragment : BindingFragment<FragmentTwoBinding>() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewBinding.ivAvatar.setImageResource(R.mipmap.ic_launcher_round)
        mViewBinding.tvName.text = "小白"
        mViewBinding.tvAge.text = 28.toString()
        mViewBinding.btn.setOnClickListener {
            Toast.makeText(mContext, "hello2", Toast.LENGTH_SHORT).show()
        }
    }
}