package com.picpay.desafio.domain.repository

import com.picpay.desafio.domain.entity.Result
import com.picpay.desafio.domain.entity.User

interface GetUsersRepository {
    suspend fun getUsers(): Result<List<User>>
}