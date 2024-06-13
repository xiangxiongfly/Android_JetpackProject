package com.example.lifecyclerdemo.advertising

import android.os.CountDownTimer

/**
 * 定义管理类
 */
class AdvertisingManager {
    private var mOnAdvertisingListener:  OnAdvertisingListener? = null
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

    /**
     * 结束计时
     */
    fun cancel() {
        countDownTimer?.cancel()
        countDownTimer = null
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