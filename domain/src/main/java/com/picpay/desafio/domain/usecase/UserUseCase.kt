package com.picpay.desafio.domain.usecase

import com.picpay.desafio.domain.entity.Result
import com.picpay.desafio.domain.entity.User

interface UserUseCase {
    suspend fun getUser(): Result<List<User>>
}