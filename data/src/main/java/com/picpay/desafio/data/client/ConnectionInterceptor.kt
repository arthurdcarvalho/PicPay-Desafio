package com.picpay.desafio.data.client

import android.content.Context
import com.picpay.desafio.data.util.NetworkImpl
import com.picpay.desafio.domain.exceptions.NoConnectionException
import okhttp3.Interceptor
import okhttp3.Response

class ConnectionInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val network = NetworkImpl(context)
        return if (network.hasInternetConnection().not()) {
            throw NoConnectionException()
        } else {
            chain.proceed(chain.request())
        }
    }
}