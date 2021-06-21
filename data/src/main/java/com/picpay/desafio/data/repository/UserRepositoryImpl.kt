package com.picpay.desafio.data.repository

import com.picpay.desafio.data.source.UserRemoteDataSource
import com.picpay.desafio.data.source.local.UserLocalDataSource
import com.picpay.desafio.domain.entity.User
import com.picpay.desafio.domain.entity.Result
import com.picpay.desafio.domain.entity.SuccessResult
import com.picpay.desafio.domain.repository.UserRepository

class UserRepositoryImpl(
    private val remoteDataSource: UserRemoteDataSource,
    private val localDataSource: UserLocalDataSource
) : UserRepository {
    override suspend fun getUser(): Result<List<User>> {
        localDataSource.getUsers().run {
            return if (isNotEmpty())
                SuccessResult(body = this)
            else
                remoteDataSource.getUsers().also {
                    if (it is SuccessResult)
                        localDataSource.saveUsers(it.body)
                }
        }
    }
}