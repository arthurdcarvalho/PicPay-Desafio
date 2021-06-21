package com.picpay.desafio.data.client

import android.content.Context
import com.google.gson.GsonBuilder
import com.picpay.desafio.data.R
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun providesOkhttp(): OkHttpClient {
    return OkHttpClient.Builder()
        .build()
}

inline fun <reified T> providesService(context: Context, okHttpClient: OkHttpClient): T {
    val gson = GsonBuilder().create()
    val retrofit = Retrofit.Builder()
        .baseUrl(context.getString(R.string.base_url))
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
    return retrofit.create(T::class.java)
}