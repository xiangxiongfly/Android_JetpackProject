package com.example.viewbindingdemo.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.example.viewbindingdemo.databinding.DialogLayoutBinding

class TipDialog(context: Context) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dialogBinding = DialogLayoutBinding.inflate(layoutInflater)
        setContentView(dialogBinding.root)
        dialogBinding.title.text = "标题"
    }
}