package com.picpay.desafio.domain

import com.picpay.desafio.domain.entity.EmptyResult
import com.picpay.desafio.domain.entity.ErrorResult
import com.picpay.desafio.domain.entity.SuccessResult
import com.picpay.desafio.domain.repository.GetUsersRepository
import com.picpay.desafio.domain.stub.UserResultStub
import com.picpay.desafio.domain.usecase.GetUserUseCase
import com.picpay.desafio.domain.usecase.GetUserUseCaseImpl
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.Before

@ExperimentalCoroutinesApi
class GetUsersUseCaseTest {

    private lateinit var useCase: GetUserUseCase

    @MockK
    private lateinit var repository: GetUsersRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCase = GetUserUseCaseImpl(repository)
    }

    @Test
    fun when_get_user_result_is_success() = runBlockingTest {
        coEvery {
            repository.getUsers()
        } returns UserResultStub.userSuccessResult()

        assertTrue(
            useCase.getUser() is SuccessResult
        )

        assertEquals(
            (useCase.getUser() as SuccessResult), UserResultStub.userSuccessResult()
        )

        coVerify { repository.getUsers() }
    }

    @Test
    fun when_get_user_result_is_empty() = runBlockingTest {

        coEvery {
            repository.getUsers()
        } returns UserResultStub.userEmptyResult()

        assertTrue(
            useCase.getUser() is EmptyResult
        )

        assertEquals(
            (useCase.getUser() as EmptyResult), UserResultStub.userEmptyResult()
        )

        coVerify { repository.getUsers() }

    }

    @Test
    fun when_get_user_result_is_error() = runBlockingTest {
        coEvery {
            repository.getUsers()
        } returns UserResultStub.userErrorResult()

        assertTrue(
            useCase.getUser() is ErrorResult
        )

        assertEquals(
            (useCase.getUser() as ErrorResult), UserResultStub.userErrorResult()
        )

        coVerify { repository.getUsers() }
    }
}
