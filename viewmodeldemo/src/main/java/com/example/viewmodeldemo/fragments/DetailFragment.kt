package com.example.viewmodeldemo.fragments

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.common.base.BaseFragment
import com.example.viewmodeldemo.R

class DetailFragment : BaseFragment(R.layout.detail_fragment) {
    private val sharedViewModel: SharedViewModel by activityViewModels()

    companion object {
        fun newInstance() = DetailFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textView = view.findViewById<TextView>(R.id.textView)
        sharedViewModel.liveData.observe(viewLifecycleOwner, object : Observer<String> {
            override fun onChanged(t: String?) {
                textView.text = t
            }
        })
    }
}