package com.example.lifecyclerdemo.dialog

import android.os.Bundle
import com.example.common.base.BaseActivity
import com.example.lifecyclerdemo.R

class DialogActivity : BaseActivity() {
    private lateinit var dialog: MyDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycle_dialog)

        dialog = MyDialog(mContext)
        dialog.show()
        postDelayed({
            finish()
        }, 2000L)
    }
}