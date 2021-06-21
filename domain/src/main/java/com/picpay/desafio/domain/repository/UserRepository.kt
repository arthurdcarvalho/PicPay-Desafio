package com.picpay.desafio.domain.repository

import com.picpay.desafio.domain.entity.Result
import com.picpay.desafio.domain.entity.User

interface UserRepository {
    suspend fun getUser(): Result<List<User>>
}