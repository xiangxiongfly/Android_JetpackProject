package com.example.viewbindingdemo.rv

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.common.base.BaseActivity
import com.example.viewbindingdemo.databinding.ActivityRvBinding
import com.example.viewbindingdemo.databinding.ItemTextBinding

class RvActivity : BaseActivity() {
    private lateinit var _viewBinding: ActivityRvBinding
    private val viewBinding get() = _viewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _viewBinding = ActivityRvBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        initRV()
    }

    private fun initRV() {
        val data = ArrayList<String>().apply {
            for (i in 1..30) {
                add("hello:$i")
            }
        }
        val adapter = MyAdapter(mContext, data)
        viewBinding.recyclerView.adapter = adapter
    }
}

class MyAdapter(private val context: Context, private val data: ArrayList<String>) :
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ItemTextBinding.inflate(layoutInflater, parent, false)
        val viewHolder = ViewHolder(itemBinding)
        viewHolder.itemView.setOnClickListener {
            Toast.makeText(context, data[viewHolder.adapterPosition], Toast.LENGTH_SHORT).show()
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.dest.text = data[position]
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(private val itemBinding: ItemTextBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        val dest: TextView = itemBinding.dest
    }
}

