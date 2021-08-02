package com.example.viewbindingdemo.base

import android.os.Bundle
import android.widget.Toast
import com.example.viewbindingdemo.R
import com.example.viewbindingdemo.databinding.DetailLayoutBinding
import com.example.viewbindingdemo.databinding.FragmentFirstBinding

class FirstFragment : BaseBindingFragment<FragmentFirstBinding>() {

    override fun initView(savedInstanceState: Bundle?) {
        mBinding!!.textView.text = "Hello Fragment"

        mBinding!!.titleBar.title.text = "这是一个标题2"
        mBinding!!.titleBar.back.setOnClickListener {
            Toast.makeText(
                mContext,
                "返回",
                Toast.LENGTH_SHORT
            ).show()
        }
        mBinding!!.titleBar.confirm.setOnClickListener {
            Toast.makeText(
                mContext,
                "确定",
                Toast.LENGTH_SHORT
            ).show()
        }

        val detailLayoutBinding = DetailLayoutBinding.bind(mBinding!!.root)
        detailLayoutBinding.ivDetail.setImageResource(R.mipmap.ic_launcher)
    }
}