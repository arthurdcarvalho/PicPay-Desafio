package com.picpay.desafio.data.source

import com.picpay.desafio.domain.entity.Result
import com.picpay.desafio.domain.entity.User

interface GetUsersRemoteDataSource {
    suspend fun getUsers(): Result<List<User>>
}