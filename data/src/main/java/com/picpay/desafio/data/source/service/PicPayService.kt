package com.picpay.desafio.data.source.service

import com.picpay.desafio.data.model.response.UserResponse
import com.picpay.desafio.data.source.service.URLs.Companion.GET_USERS
import retrofit2.Response
import retrofit2.http.GET

interface PicPayService {
    @GET(GET_USERS)
    suspend fun getUsers(): Response<List<UserResponse>>
}