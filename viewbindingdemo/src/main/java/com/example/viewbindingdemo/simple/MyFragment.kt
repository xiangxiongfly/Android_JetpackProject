package com.example.jetpack.viewbinding.simple

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.common.base.BaseFragment
import com.example.viewbindingdemo.R
import com.example.viewbindingdemo.databinding.FragmentMyBinding

class MyFragment : BaseFragment() {
    private var _viewBinding: FragmentMyBinding? = null
    private val viewBinding get() = _viewBinding!!

    // 方式一：
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentMyBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.tvName.text = "hello world"
        viewBinding.tvAge.text = 18.toString()
        viewBinding.ivAvatar.setImageResource(R.mipmap.ic_launcher_round)
        viewBinding.btn.setOnClickListener {
            Toast.makeText(mContext, "hello", Toast.LENGTH_SHORT).show()
        }
    }

    // 方式二：
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
//    ): View {
//        return inflater.inflate(R.layout.fragment_my, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        _viewBinding = FragmentMyBinding.bind(view)
//        viewBinding.tvName.text = "hello world"
//        viewBinding.tvAge.text = 18.toString()
//        viewBinding.ivAvatar.setImageResource(R.mipmap.ic_launcher_round)
//        viewBinding.btn.setOnClickListener {
//            Toast.makeText(mContext, "hello", Toast.LENGTH_SHORT).show()
//        }
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }
}