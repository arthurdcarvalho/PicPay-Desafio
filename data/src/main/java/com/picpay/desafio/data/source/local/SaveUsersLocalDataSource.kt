package com.picpay.desafio.data.source.local

import com.picpay.desafio.domain.entity.User

interface SaveUsersLocalDataSource {
    suspend fun saveUsers(users: List<User>)
}