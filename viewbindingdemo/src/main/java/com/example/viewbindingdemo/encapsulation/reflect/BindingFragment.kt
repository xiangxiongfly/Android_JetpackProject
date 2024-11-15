package com.example.viewbindingdemo.encapsulation.reflect

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.example.common.base.BaseFragment
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

abstract class BindingFragment<VB : ViewBinding> : BaseFragment() {
    private var _viewBinding: VB? = null
    protected val mViewBinding get() = _viewBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val type: Type? = javaClass.genericSuperclass
        if (type != null && type is ParameterizedType) {
            val clz = type.actualTypeArguments[0] as Class<*>
            val method = clz.getMethod(
                "inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java
            )
            _viewBinding = method.invoke(null, inflater, container, false) as VB
        }
        return mViewBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }
}