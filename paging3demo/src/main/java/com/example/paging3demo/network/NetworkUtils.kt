package com.example.paging3demo.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class NetworkUtils {

    fun isConnected(context: Context): Boolean {
        val info = getActiveNetworkInfo(context)
        return info?.isConnected ?: false
    }

    private fun getActiveNetworkInfo(context: Context): NetworkInfo? {
        val cm = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo
    }
}