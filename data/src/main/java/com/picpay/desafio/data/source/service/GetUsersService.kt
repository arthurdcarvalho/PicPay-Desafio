package com.picpay.desafio.data.source.service

import com.picpay.desafio.data.model.response.UserResponse
import com.picpay.desafio.data.source.service.URLs.GET_USERS
import retrofit2.Response
import retrofit2.http.GET

interface GetUsersService {
    @GET(GET_USERS)
    suspend fun getUsers(): Response<List<UserResponse>>
}