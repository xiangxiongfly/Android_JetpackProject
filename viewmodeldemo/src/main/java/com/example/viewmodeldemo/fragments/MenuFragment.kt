package com.example.viewmodeldemo.fragments

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.activityViewModels
import com.example.common.base.BaseFragment
import com.example.viewmodeldemo.R

class MenuFragment : BaseFragment(R.layout.fragment_menu) {
    private val sharedViewModel: SharedViewModel by activityViewModels()

    companion object {
        fun newInstance() = MenuFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listView = view.findViewById<ListView>(R.id.listView)
        val data = ArrayList<String>().apply {
            for (i in 0..30) {
                add("menu$i")
            }
        }
        listView.adapter = ArrayAdapter(mContext, android.R.layout.simple_list_item_1, data)
        listView.setOnItemClickListener { parent, view, position, id ->
            sharedViewModel.setData(data[position])
        }
    }
}