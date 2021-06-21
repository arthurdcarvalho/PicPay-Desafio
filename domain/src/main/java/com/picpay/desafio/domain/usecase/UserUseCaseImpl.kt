package com.picpay.desafio.domain.usecase

import com.picpay.desafio.domain.entity.Result
import com.picpay.desafio.domain.entity.User
import com.picpay.desafio.domain.repository.UserRepository

class UserUseCaseImpl(
    private val  repository: UserRepository
): UserUseCase {
    override suspend fun getUser(): Result<List<User>> {
        return repository.getUser()
    }
}
