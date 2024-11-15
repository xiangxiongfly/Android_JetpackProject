package com.example.viewbindingdemo.encapsulation.reflect

import android.os.Bundle
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import com.example.common.base.BaseActivity
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

abstract class BindingActivity<VB : ViewBinding> : BaseActivity() {
    private lateinit var _viewBinding: VB
    protected val mViewBinding get() = _viewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val type: Type? = javaClass.genericSuperclass //获取父类的泛型类型
        if (type != null && type is ParameterizedType) {
            val clz = type.actualTypeArguments[0] as Class<*>
            val method = clz.getMethod("inflate", LayoutInflater::class.java)
            _viewBinding = method.invoke(null, layoutInflater) as VB
            setContentView(_viewBinding.root)
        }
    }
}