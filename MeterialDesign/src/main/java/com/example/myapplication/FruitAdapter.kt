package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FruitAdapter(private val context: Context, private val fruitList: List<Fruit>) :
    RecyclerView.Adapter<FruitAdapter.ViewHolder>() {


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val fruitImage: ImageView = itemView.findViewById(R.id.fruitImage)
        val fruitName: TextView = itemView.findViewById(R.id.fruitName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var layoutInflater: LayoutInflater = LayoutInflater.from(context)
        val viewHolder = ViewHolder(layoutInflater.inflate(R.layout.item_fruit, parent, false))
        viewHolder.itemView.setOnClickListener {
            val fruit = fruitList[viewHolder.layoutPosition]
            DetailActivity.actionStart(context, fruit.fruitName, fruit.fruitImage)
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.fruitImage.setImageResource(fruitList[position].fruitImage)
        holder.fruitName.text = fruitList[position].fruitName
    }

    override fun getItemCount(): Int = fruitList.size
}