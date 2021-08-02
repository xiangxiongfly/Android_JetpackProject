package com.example.viewbindingdemo.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

abstract class BaseBindingFragment<VB : ViewBinding> : Fragment() {

    protected var mBinding: VB? = null

    protected lateinit var mContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val type = javaClass.genericSuperclass
        if (type is ParameterizedType) {
            val clz = type.actualTypeArguments[0] as Class<VB>
            val method = clz.getMethod(
                "inflate",
                LayoutInflater::class.java,
                ViewGroup::class.java,
                Boolean::class.javaPrimitiveType
            )
            mBinding = method.invoke(null, inflater, container, false) as VB
        }
        return mBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(savedInstanceState)
    }

    abstract fun initView(savedInstanceState: Bundle?)

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }
}