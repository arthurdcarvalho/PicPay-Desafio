package com.picpay.desafio.domain.stub

import com.picpay.desafio.domain.entity.EmptyResult
import com.picpay.desafio.domain.entity.ErrorResult
import com.picpay.desafio.domain.entity.SuccessResult
import com.picpay.desafio.domain.entity.User
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString

object UserResultStub {
    fun userSuccessResult() = SuccessResult(
        listOf(
            User(
                id = 1,
                name = "Sandrine",
                username = "Tod86",
                img = "https://randomuser.me/api/portraits/men/1.jpg"
            )
        )
    )

    fun userEmptyResult() = EmptyResult<List<User>>(
        error = anyString(),
        message = anyString()
    )

    fun userErrorResult() = ErrorResult<List<User>>(
        code = anyInt(),
        error = anyString()
    )
}