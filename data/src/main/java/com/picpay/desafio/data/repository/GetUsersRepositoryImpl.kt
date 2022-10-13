package com.picpay.desafio.data.repository

import com.picpay.desafio.data.source.GetUsersRemoteDataSource
import com.picpay.desafio.data.source.local.GetUsersLocalDataSource
import com.picpay.desafio.data.source.local.SaveUsersLocalDataSource
import com.picpay.desafio.domain.entity.EmptyResult
import com.picpay.desafio.domain.entity.ErrorResult
import com.picpay.desafio.domain.entity.Result
import com.picpay.desafio.domain.entity.SuccessResult
import com.picpay.desafio.domain.entity.User
import com.picpay.desafio.domain.exceptions.NoConnectionException
import com.picpay.desafio.domain.repository.GetUsersRepository

class GetUsersRepositoryImpl(
    private val remoteDataSource: GetUsersRemoteDataSource,
    private val getUsersLocalDataSource: GetUsersLocalDataSource,
    private val saveUsersLocalDataSource: SaveUsersLocalDataSource
) : GetUsersRepository {
    override suspend fun getUsers(): Result<List<User>> {
        return try {
            remoteDataSource.getUsers().also { result ->
                if (result is SuccessResult)
                    saveUsersLocalDataSource.saveUsers(result.body)
            }
        } catch (exception: NoConnectionException) {
            getUsersLocalDataSource.getUsers().run {
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
