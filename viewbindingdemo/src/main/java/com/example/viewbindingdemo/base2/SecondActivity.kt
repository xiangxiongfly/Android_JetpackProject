package com.example.viewbindingdemo.base2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.viewbindingdemo.R
import com.example.viewbindingdemo.databinding.ActivitySecondBinding
import com.example.viewbindingdemo.databinding.DetailLayoutBinding
import com.example.viewbindingdemo.toast
import com.hi.dhl.jdatabinding.binding

class SecondActivity : AppCompatActivity() {

    private val mBinding: ActivitySecondBinding by binding()

    private lateinit var detailLayoutBinding: DetailLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding.textView.text = "Hello World"

        mBinding.titleBar.title.text = "这是一个标题"
        mBinding.titleBar.back.setOnClickListener {
            toast("返回")
        }
        mBinding.titleBar.confirm.setOnClickListener {
            toast("确定")
        }

        detailLayoutBinding = DetailLayoutBinding.bind(mBinding.root)
        detailLayoutBinding.ivDetail.setImageResource(R.mipmap.ic_launcher)
    }
}