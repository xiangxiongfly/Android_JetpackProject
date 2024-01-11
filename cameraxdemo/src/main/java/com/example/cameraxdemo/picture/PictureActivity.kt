package com.example.cameraxdemo.picture

import android.content.ContentValues
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Size
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupWindow
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import com.example.cameraxdemo.R
import com.example.cameraxdemo.base.BaseActivity
import com.example.cameraxdemo.databinding.ActivityPictureBinding
import com.example.cameraxdemo.dp2px
import com.example.cameraxdemo.logE
import java.util.concurrent.Executor

class PictureActivity : BaseActivity() {
    private lateinit var mBinding: ActivityPictureBinding

    private lateinit var mPreview: Preview
    private lateinit var mCamera: Camera
    private lateinit var mCameraProvider: ProcessCameraProvider
    private lateinit var mImageAnalysis: ImageAnalysis
    private lateinit var mImageCapture: ImageCapture
    private var singleTapDetector: GestureDetector? = null
    private val mExecutor: Executor by lazy {
        ContextCompat.getMainExecutor(this)
    }
    private var isBack = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityPictureBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        mBinding.root.post {
            initCameraX()
        }
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.iv_change -> changeCamera()
            R.id.iv_capture -> takeCapture()
        }
    }

    private fun initCameraX() {
        // 请求CameraProvider
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        cameraProviderFuture.addListener({
            mCameraProvider = cameraProviderFuture.get()
            bindPreview(mCameraProvider, mBinding.previewView)
            listenGesture()
        }, mExecutor)
    }

    private fun bindPreview(cameraProvider: ProcessCameraProvider, previewView: PreviewView) {
        // 切换前后摄像头
        val cameraSelector =
            if (isBack) CameraSelector.DEFAULT_BACK_CAMERA else CameraSelector.DEFAULT_FRONT_CAMERA

        // 创建Preview
        mPreview = Preview.Builder().build()

        // 设置图片拍摄
        mImageCapture = ImageCapture.Builder()
            .setTargetRotation(previewView.display.rotation)
//            .setCaptureMode() // 设置拍摄模式
//            .setFlashMode() // 设置闪光模式
            .build()

        // 设置图片质量
        mImageAnalysis = ImageAnalysis.Builder()
            .setTargetRotation(previewView.display.rotation)
            .setTargetResolution(Size(720, 1440))
            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
            .build()

        // 绑定前确保解除了所有绑定，防止CameraProvider重复绑定到Lifecycle发生异常
        cameraProvider.unbindAll()

        // 绑定生命周期
        mCamera = cameraProvider.bindToLifecycle(
            this,
            cameraSelector,
            mPreview,
            mImageCapture,
            mImageAnalysis
        )

        // 将Preview连接到PreviewView
        mPreview.setSurfaceProvider(previewView.surfaceProvider)
    }

    /**
     * 监听手势
     */
    private fun listenGesture() {
        mBinding.previewView.setOnTouchListener { view, event ->
            singleTapForFocus(event)
            true
        }
        focusOnPosition(
            (mBinding.previewView.width / 2).toFloat(),
            (mBinding.previewView.height / 2).toFloat(),
        )
    }

    /**
     * 根据坐标聚焦
     */
    private fun focusOnPosition(x: Float, y: Float, isShowTapView: Boolean = false) {
        logE("position: $x - $y")
        logE("width: ${mBinding.previewView.width}  height: ${mBinding.previewView.height}")
        val action = FocusMeteringAction.Builder(
            mBinding.previewView.meteringPointFactory.createPoint(x, y)
        ).build()
        if (isShowTapView) {
            showTapView(x.toInt(), y.toInt())
        }
        mCamera.cameraControl.startFocusAndMetering(action)
    }

    /**
     * 显示聚焦的图标
     */
    private fun showTapView(x: Int, y: Int) {
        val imageView = ImageView(this)
            .apply {
                setImageResource(R.drawable.ic_focus_view)
            }
        val popupWindow = PopupWindow(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        ).apply {
            contentView = imageView
        }
        val offset = dp2px(50)
        popupWindow.showAsDropDown(mBinding.previewView, x - offset / 2, y - offset / 2)
        mBinding.previewView.postDelayed({ popupWindow.dismiss() }, 500L)
    }

    /**
     * 单击聚焦
     */
    private fun singleTapForFocus(event: MotionEvent) {
        if (singleTapDetector == null) {
            singleTapDetector = GestureDetector(this@PictureActivity,
                object : GestureDetector.SimpleOnGestureListener() {
                    override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
                        focusOnPosition(event.x, event.y, true)
                        return super.onSingleTapConfirmed(e)
                    }
                })
        }
        singleTapDetector!!.onTouchEvent(event)
    }

    /**
     * 前后摄像头切换
     */
    private fun changeCamera() {
        isBack = !isBack
        bindPreview(mCameraProvider, mBinding.previewView)
        mImageAnalysis.clearAnalyzer()
    }

    /**
     * 拍照
     */
    private fun takeCapture() {
        val contentValues = ContentValues()
            .apply {
                put(
                    MediaStore.MediaColumns.DISPLAY_NAME,
                    "my_capture_${System.currentTimeMillis()}"
                )
                put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            }
        val outputFileOptions = ImageCapture.OutputFileOptions.Builder(
            contentResolver,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues
        ).build()
        mImageCapture.takePicture(
            outputFileOptions,
            mExecutor,
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    val uri = outputFileResults.savedUri ?: return
                    showPicture(uri)
                }

                override fun onError(exception: ImageCaptureException) {
                    logE("onError: ${exception.imageCaptureError}")
                }
            })
    }

    /**
     * 显示图片
     */
    private fun showPicture(uri: Uri) {
        mBinding.rlShowCapture.visibility = View.VISIBLE
//        UriUtils.getRealPath(this, uri)?.let {
//            val bitmap = BitmapFactory.decodeFile(it)
//            mBinding.ivShowCapture.setImageBitmap(bitmap)
//        }
        mBinding.ivShowCapture.setImageURI(uri)
        mBinding.tvHideCapture.setOnClickListener {
            mBinding.ivShowCapture.setImageDrawable(null)
            mBinding.rlShowCapture.visibility = View.GONE
        }
    }
}