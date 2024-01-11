package com.example.cameraxdemo.video

import android.Manifest
import android.content.ContentValues
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import androidx.camera.core.Camera
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.core.VideoCapture
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.cameraxdemo.base.BaseActivity
import com.example.cameraxdemo.databinding.ActivityVideoBinding
import com.example.cameraxdemo.logE
import com.example.cameraxdemo.showToast
import java.util.concurrent.Executor

class VideoActivity : BaseActivity() {
    private lateinit var mBinding: ActivityVideoBinding

    private lateinit var mPreview: Preview
    private lateinit var mCamera: Camera
    private lateinit var mCameraProvider: ProcessCameraProvider
    private lateinit var mVideoCapture: VideoCapture
    private val mExecutor: Executor by lazy {
        ContextCompat.getMainExecutor(mContext)
    }
    private var isRecording = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityVideoBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        mBinding.root.post {
            initCameraX()
        }
        mBinding.ivVideo.setOnClickListener {
            if (!isRecording) startRecordVideo() else stopRecordVideo()
        }
    }

    private fun initCameraX() {
        val listenableFuture = ProcessCameraProvider.getInstance(this)
        listenableFuture.addListener({
            mCameraProvider = listenableFuture.get()
            bindPreview(mCameraProvider, mBinding.previewView)
        }, mExecutor)
    }

    private fun bindPreview(cameraProvider: ProcessCameraProvider, previewView: PreviewView) {
        // 设置摄像头
        val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

        // 创建Preview
        mPreview = Preview.Builder().build()

        mVideoCapture = VideoCapture.Builder()
            .setTargetRotation(previewView.display.rotation)
            .setVideoFrameRate(25)
            .setBitRate(3 * 1024 * 1024)
            .build()

        // 绑定前确保解除了所有绑定，防止CameraProvider重复绑定到Lifecycle发生异常
        cameraProvider.unbindAll()

        // 绑定生命周期
        mCamera = cameraProvider.bindToLifecycle(
            this,
            cameraSelector,
            mPreview,
            mVideoCapture
        )

        // 将Preview连接到PreviewView
        mPreview.setSurfaceProvider(previewView.surfaceProvider)
    }

    private fun startRecordVideo() {
        isRecording = true
        mBinding.tvVideo.text = "停止录制"

        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, "my_video" + "_" + System.currentTimeMillis())
            put(MediaStore.MediaColumns.MIME_TYPE, "video/mp4")
        }
        val options = VideoCapture.OutputFileOptions.Builder(
            contentResolver,
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
            contentValues
        ).build()
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.RECORD_AUDIO
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        mVideoCapture.startRecording(
            options,
            mExecutor,
            object : VideoCapture.OnVideoSavedCallback {
                override fun onVideoSaved(outputFileResults: VideoCapture.OutputFileResults) {
                    val uri = outputFileResults.savedUri
                    uri?.let {
                        val path = it.path
                        path?.let {
                            showToast("视频保存在：$path")
                            logE("视频保存在：$path")
                        }
                    }
                }

                override fun onError(videoCaptureError: Int, message: String, cause: Throwable?) {
                    showToast("保存失败：$message")
                    logE("保存失败：$message")
                }
            }
        )
    }

    private fun stopRecordVideo() {
        isRecording = false
        mBinding.tvVideo.text = "开始录制"
        mVideoCapture.stopRecording()
    }
}