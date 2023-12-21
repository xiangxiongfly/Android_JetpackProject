package com.example.paging3demo.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.paging3demo.R
import com.example.paging3demo.repository.bean.User

class UserAdapter(val updateItem: (Int, User) -> Unit) :
    PagingDataAdapter<User, UserAdapter.ViewHolder>(diffCallback) {

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tv_name)
        val tvFullName: TextView = itemView.findViewById(R.id.tv_full_name)
        val tvCount: TextView = itemView.findViewById(R.id.tv_count)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_description)
        val btnUpdate: Button = itemView.findViewById(R.id.btn_update)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = getItem(position)
        user?.let {
            holder.tvName.text = it.name
            holder.tvFullName.text = it.fullName
            holder.tvDescription.text = it.description
            holder.tvCount.text = it.starCount.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        val viewHolder = ViewHolder(itemView)
        viewHolder.btnUpdate.setOnClickListener {
            val position = viewHolder.layoutPosition
            val user = getItem(position)
            updateItem(position, user!!)
            notifyItemChanged(position)
        }
        return viewHolder
    }
}