package com.example.cameraxdemo

import android.content.Intent
import android.os.Bundle
import androidx.annotation.NonNull
import com.example.cameraxdemo.base.BaseActivity
import com.example.cameraxdemo.databinding.ActivityMainBinding
import com.example.cameraxdemo.picture.PictureActivity
import com.example.cameraxdemo.video.VideoActivity
import com.hjq.permissions.OnPermissionCallback
import com.hjq.permissions.Permission
import com.hjq.permissions.XXPermissions

class MainActivity : BaseActivity() {
    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.btnPicture.setOnClickListener {
            toPictureActivity()
        }
        mBinding.btnVideo.setOnClickListener {
            toVideoActivity()
        }
    }

    private fun toPictureActivity() {
        if (XXPermissions.isGranted(this, Permission.CAMERA, Permission.WRITE_EXTERNAL_STORAGE)) {
            startActivity(Intent(this, PictureActivity::class.java))
        } else {
            requestPermissions()
        }
    }

    private fun toVideoActivity() {
        if (XXPermissions.isGranted(
                this,
                Permission.CAMERA,
                Permission.WRITE_EXTERNAL_STORAGE,
                Permission.RECORD_AUDIO
            )
        ) {
            startActivity(Intent(this, VideoActivity::class.java))
        } else {
            requestPermissions()
        }
    }

    private fun requestPermissions() {
        XXPermissions.with(this)
            .permission(Permission.CAMERA)
            .permission(Permission.WRITE_EXTERNAL_STORAGE)
            .permission(Permission.RECORD_AUDIO)
            .request(object : OnPermissionCallback {
                override fun onGranted(@NonNull permissions: List<String>, allGranted: Boolean) {
                    if (!allGranted) {
                        showToast("获取部分权限成功，但部分权限未正常授予");
                        return;
                    }
                }

                override fun onDenied(@NonNull permissions: List<String>, doNotAskAgain: Boolean) {

                }
            })
    }
}