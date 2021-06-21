package com.picpay.desafio.data.source.local

import com.picpay.desafio.domain.entity.User

interface UserLocalDataSource {
    suspend fun getUsers(): List<User>
    suspend fun saveUsers(users: List<User>)
}