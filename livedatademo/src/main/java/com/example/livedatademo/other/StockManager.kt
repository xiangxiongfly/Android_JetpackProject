package com.example.livedatademo.other

import java.util.*
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.concurrent.thread

class StockManager {
    private val list = Collections.synchronizedList(arrayListOf<UpdateListener>())
    private var isStop = AtomicBoolean(false)

    fun start() {
        isStop.set(false)
        thread {
            while (!isStop.get() && list.size > 0) {
                for (i in list) {
                    i.onUpdate(System.currentTimeMillis().toString())
                }
                Thread.sleep(1000L)
            }
        }
    }

    fun stop() {
        isStop.set(true)
    }

    fun registerUpdates(listener: UpdateListener) {
        list.add(listener)
    }

    fun unregisterUpdates(listener: UpdateListener) {
        list.remove(listener)
    }
}

interface UpdateListener {
    fun onUpdate(price: String)
}