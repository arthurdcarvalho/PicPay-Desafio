package com.picpay.desafio.domain

import com.picpay.desafio.domain.entity.EmptyResult
import com.picpay.desafio.domain.entity.ErrorResult
import com.picpay.desafio.domain.entity.SuccessResult
import com.picpay.desafio.domain.repository.UserRepository
import com.picpay.desafio.domain.stub.UserResultStub
import com.picpay.desafio.domain.usecase.UserUseCase
import com.picpay.desafio.domain.usecase.UserUseCaseImpl
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
class UserUseCaseTest {

    private lateinit var useCase: UserUseCase

    @MockK
    private lateinit var repository: UserRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCase = UserUseCaseImpl(repository)
    }

    @Test
    fun when_get_user_result_is_success() = runBlockingTest {
        coEvery {
            repository.getUser()
        } returns UserResultStub.userSuccessResult()

        assertTrue(
            useCase.getUser() is SuccessResult
        )

        assertEquals(
            (useCase.getUser() as SuccessResult), UserResultStub.userSuccessResult()
        )

        coVerify { repository.getUser() }
    }

    @Test
    fun when_get_user_result_is_empty() = runBlockingTest {

        coEvery {
            repository.getUser()
        } returns UserResultStub.userEmptyResult()

        assertTrue(
            useCase.getUser() is EmptyResult
        )

        assertEquals(
            (useCase.getUser() as EmptyResult), UserResultStub.userEmptyResult()
        )

        coVerify { repository.getUser() }

    }

    @Test
    fun when_get_user_result_is_error() = runBlockingTest {
        coEvery {
            repository.getUser()
        } returns UserResultStub.userErrorResult()

        assertTrue(
            useCase.getUser() is ErrorResult
        )

        assertEquals(
            (useCase.getUser() as ErrorResult), UserResultStub.userErrorResult()
        )

        coVerify { repository.getUser() }
    }
}