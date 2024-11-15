package com.example.viewbindingdemo.encapsulation.delegate

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.common.base.BaseFragment
import com.example.viewbindingdemo.R
import com.example.viewbindingdemo.databinding.FragmentThreeBinding

class ThreeFragment : BaseFragment(R.layout.fragment_three) {
    private val viewBinding: FragmentThreeBinding by viewBindings(FragmentThreeBinding::bind)
//    private val viewBinding: FragmentThreeBinding by viewBindings()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.ivAvatar.setImageResource(R.mipmap.ic_launcher_round)
        viewBinding.tvName.text = "小白33"
        viewBinding.tvAge.text = 338.toString()
        viewBinding.btn.setOnClickListener {
            Toast.makeText(mContext, "hello33", Toast.LENGTH_SHORT).show()
        }
    }
}