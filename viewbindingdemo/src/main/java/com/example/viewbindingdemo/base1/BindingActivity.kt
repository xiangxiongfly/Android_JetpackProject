package com.example.viewbindingdemo.base1

import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.example.common.base.BaseActivity

abstract class BindingActivity<VB : ViewBinding> : BaseActivity() {
    private lateinit var _viewBinding: VB
    protected val mViewBinding get() = _viewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _viewBinding = getViewBinding()
        setContentView(_viewBinding.root)
    }

    abstract fun getViewBinding(): VB
}