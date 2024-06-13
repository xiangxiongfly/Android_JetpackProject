package com.example.lifecyclerdemo.advertising2

import android.os.CountDownTimer
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

/**
 * 定义管理类
 */
class AdvertisingManager2 : DefaultLifecycleObserver {
    private var mOnAdvertisingListener: OnAdvertisingListener? = null
    private var countDownTimer: CountDownTimer? = null

    /**
     * 开始计时
     */
    fun start() {
        countDownTimer = object : CountDownTimer(5000L, 1000L) {
            override fun onTick(millisUntilFinished: Long) {
                mOnAdvertisingListener?.time((millisUntilFinished / 1000L).toInt())
            }

            override fun onFinish() {
                mOnAdvertisingListener?.jumpToActivity()
            }
        }
        countDownTimer!!.start()
    }

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        start()
    }

    /**
     * 结束计时
     */
    fun cancel() {
        countDownTimer?.cancel()
        countDownTimer = null
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        cancel()
    }

    fun setOnAdvertisingListener(onAdvertisingListener: OnAdvertisingListener) {
        mOnAdvertisingListener = onAdvertisingListener
    }

    interface OnAdvertisingListener {
        /**
         * 计时
         */
        fun time(second: Int)

        /**
         * 调整下一页
         */
        fun jumpToActivity()
    }
}