package com.picpay.desafio.data.util

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build

class NetworkImpl(
    private val context: Context
): Network {
    override fun hasInternetConnection(): Boolean {
        return (context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager)?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                it.activeNetwork != null
            } else {
                @Suppress("DEPRECATION")
                it.activeNetworkInfo != null
            }
        } ?: false
    }
}