package com.example.viewbindingdemo.base2

import android.os.Bundle
import android.view.View
import com.example.viewbindingdemo.R
import com.example.viewbindingdemo.databinding.DetailLayoutBinding
import com.example.viewbindingdemo.databinding.FragmentSecondBinding
import com.example.viewbindingdemo.toast
import com.hi.dhl.jdatabinding.DataBindingFragment


class SecondFragment : DataBindingFragment(R.layout.fragment_second) {
    private val mBinding: FragmentSecondBinding by binding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.textView.text = "Hello Fragment"

        mBinding.titleBar.title.text = "这是一个标题2"
        mBinding.titleBar.back.setOnClickListener {
            toast("返回")
        }
        mBinding.titleBar.confirm.setOnClickListener {
            toast("确定")
        }

        val detailLayoutBinding = DetailLayoutBinding.bind(mBinding.root)
        detailLayoutBinding.ivDetail.setImageResource(R.mipmap.ic_launcher)
    }

}