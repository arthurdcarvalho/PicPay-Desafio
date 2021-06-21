package com.picpay.desafio.data.repository

import com.picpay.desafio.data.source.UserRemoteDataSource
import com.picpay.desafio.data.source.local.UserLocalDataSource
import com.picpay.desafio.data.stub.UserStub
import com.picpay.desafio.domain.entity.SuccessResult
import com.picpay.desafio.domain.repository.UserRepository
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class UserRepositoryTest {
    private lateinit var repository: UserRepository

    @MockK
    private lateinit var userRemoteDataSource: UserRemoteDataSource

    @MockK
    private lateinit var userLocalDataSource: UserLocalDataSource

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        repository = UserRepositoryImpl(userRemoteDataSource, userLocalDataSource)
    }

    @Test
    fun when_get_user_result_users_from_remote() = runBlockingTest {

        coEvery {
            userLocalDataSource.getUsers()
        } returns emptyList()

        coEvery {
            userLocalDataSource.saveUsers(UserStub.getUserListSuccessResult().body)
        } returns Unit

        coEvery {
            userRemoteDataSource.getUsers()
        } returns UserStub.getUserListSuccessResult()

        assertEquals(
            UserStub.getUserListSuccessResult(),
            repository.getUser()
        )

        coVerify { userRemoteDataSource.getUsers() }
    }

    @Test
    fun when_get_user_from_local() = runBlockingTest {
        coEvery {
            userLocalDataSource.getUsers()
        } returns UserStub.getUserList()

        assertEquals(
            SuccessResult(UserStub.getUserList()),
            repository.getUser() as SuccessResult
        )
    }

}