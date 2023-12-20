package com.example.paging3demo.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.paging3demo.R
import com.example.paging3demo.utils.logE

class FooterAdapter(val retry: () -> Unit) : LoadStateAdapter<FooterAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val progressBar: ProgressBar = itemView.findViewById(R.id.progress_bar)
        val btnRetry: Button = itemView.findViewById(R.id.btn_retry)
    }

    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {
        holder.progressBar.isVisible = loadState is LoadState.Loading
        holder.btnRetry.isVisible = loadState is LoadState.Error
        logE("loadState $loadState")
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_footer, parent, false)
        val viewHolder = ViewHolder(itemView)
        viewHolder.btnRetry.setOnClickListener {
            retry()
        }
        return viewHolder
    }
}