package com.example.myapplication

import android.app.ProgressDialog
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.entity.DataResult

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    private lateinit var loadingDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadingDialog = ProgressDialog(this)

        binding.btnRequest.setOnClickListener {
            showLoading()
            viewModel.search1(1, "鸿洋").observe(this) {
                when (it) {
                    is DataResult.Success -> {
                        hideLoading()
                        binding.tvContent.text = it.response.toString()
                    }
                    is DataResult.Error -> {
                        hideLoading()
                        toast(it.exception.msg)
                    }
                }
            }
        }

        binding.btnRequest2.setOnClickListener {
            showLoading()
            viewModel.search2(1, "鸿洋").observe(this) {
                when (it) {
                    is DataResult.Success -> {
                        hideLoading()
                        binding.tvContent.text = it.response.toString()
                    }
                    is DataResult.Error -> {
                        hideLoading()
                        toast(it.exception.msg)
                    }
                }
            }
        }
    }

    private fun toast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun showLoading() {
        loadingDialog.show()
    }

    private fun hideLoading() {
        loadingDialog.dismiss()
    }
}