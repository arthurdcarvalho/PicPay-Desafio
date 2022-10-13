package com.picpay.desafio.data.source.local

import com.picpay.desafio.domain.entity.User

interface GetUsersLocalDataSource {
    suspend fun getUsers(): List<User>
}