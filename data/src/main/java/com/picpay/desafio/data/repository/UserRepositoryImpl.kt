package com.picpay.desafio.data.repository

import com.picpay.desafio.data.source.UserRemoteDataSource
import com.picpay.desafio.data.source.local.UserLocalDataSource
import com.picpay.desafio.domain.entity.*
import com.picpay.desafio.domain.exceptions.NoConnectionException
import com.picpay.desafio.domain.repository.UserRepository
import java.lang.Exception

class UserRepositoryImpl(
    private val remoteDataSource: UserRemoteDataSource,
    private val localDataSource: UserLocalDataSource
) : UserRepository {
    override suspend fun getUser(): Result<List<User>> {
        return try {
            remoteDataSource.getUsers().also { result ->
                if (result is SuccessResult)
                    localDataSource.saveUsers(result.body)
            }
        } catch (exception: NoConnectionException) {
            localDataSource.getUsers().run {
                if (isNotEmpty())
                    SuccessResult(body = this)
                else
                    EmptyResult(EMPTY_LIST_ERROR, EMPTY_LIST_MESSAGE)
            }
        } catch (exception: Exception) {
            ErrorResult(null, exception.message.orEmpty())
        }
    }

    private companion object {
        const val EMPTY_LIST_ERROR = "Empty list"
        const val EMPTY_LIST_MESSAGE = "No Users saved"
    }
}