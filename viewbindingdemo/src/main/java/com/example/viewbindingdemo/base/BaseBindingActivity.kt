package com.example.viewbindingdemo.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

abstract class BaseBindingActivity<VB : ViewBinding> : AppCompatActivity() {

    protected lateinit var mBinding: VB

    protected lateinit var mContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        val type = javaClass.genericSuperclass
        if (type is ParameterizedType) {
            val clz = type.actualTypeArguments[0] as Class<VB>
            val method = clz.getMethod("inflate", LayoutInflater::class.java)
            mBinding = method.invoke(null, layoutInflater) as VB
            setContentView(mBinding.root)
        }
        initView(savedInstanceState)
    }

    protected abstract fun initView(savedInstanceState: Bundle?)
}