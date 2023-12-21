package com.example.paging3demo.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.paging3demo.R
import com.example.paging3demo.repository.bean.User
import com.example.paging3demo.ui.adapter.UserAdapter
import com.example.paging3demo.utils.logE
import com.example.paging3demo.utils.showToast
import com.example.paging3demo.viewmodel.MainViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var btnRefresh: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var rvUsers: RecyclerView
    private val viewModel: MainViewModel by viewModels()
    private lateinit var mAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        observe()
    }

    private fun initViews() {
        btnRefresh = findViewById(R.id.btn_refresh)
        progressBar = findViewById(R.id.progress_bar)
        rvUsers = findViewById(R.id.rv_users)
        initRv()
    }

    private fun initRv() {
        rvUsers.layoutManager = LinearLayoutManager(this)
//        mAdapter = UserAdapter()
        mAdapter = UserAdapter { position, user ->
            user.fullName = System.currentTimeMillis().toString()
        }
        rvUsers.adapter = mAdapter
        // 添加footer
//        rvUsers.adapter = mAdapter.withLoadStateFooter(FooterAdapter {
//            // 重试
//            mAdapter.retry()
//        })
        // 刷新
        btnRefresh.setOnClickListener {
            mAdapter.refresh()
        }
    }

    private lateinit var pagingData: PagingData<User>

    private fun observe() {
        lifecycleScope.launch {
            // 监听数据
            viewModel.getPagingData().collect {
                pagingData = it
                // 将数据传递给适配器
                mAdapter.submitData(it)
            }
        }

        // 监听加载状态
        mAdapter.addLoadStateListener {
            when (it.refresh) {
                is LoadState.NotLoading -> {
                    progressBar.visibility = View.INVISIBLE
                    rvUsers.visibility = View.VISIBLE
                    logE("无加载")
                }
                is LoadState.Loading -> {
                    progressBar.visibility = View.VISIBLE
                    rvUsers.visibility = View.INVISIBLE
                    logE("正在加载")
                }
                is LoadState.Error -> {
                    val state = it.refresh as LoadState.Error
                    progressBar.visibility = View.INVISIBLE
                    showToast("加载失败")
                    logE("加载失败：${state.error.message}")
                }
            }
        }
    }

}