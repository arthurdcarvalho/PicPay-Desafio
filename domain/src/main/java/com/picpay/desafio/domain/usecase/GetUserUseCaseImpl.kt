package com.picpay.desafio.domain.usecase

import com.picpay.desafio.domain.entity.Result
import com.picpay.desafio.domain.entity.User
import com.picpay.desafio.domain.repository.GetUsersRepository

class GetUserUseCaseImpl(
    private val repository: GetUsersRepository
) : GetUserUseCase {
    override suspend fun getUser(): Result<List<User>> = repository.getUsers()
}
