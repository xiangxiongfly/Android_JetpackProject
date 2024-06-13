package com.example.lifecyclerdemo.simple

interface IObserver {
    fun dispatchCreate()
    fun dispatchStart()
    fun dispatchResume()
    fun dispatchPause()
    fun dispatchStop()
    fun dispatchDestroy()
}