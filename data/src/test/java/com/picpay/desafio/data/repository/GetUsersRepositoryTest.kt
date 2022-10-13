package com.picpay.desafio.data.repository

import com.picpay.desafio.data.source.GetUsersRemoteDataSource
import com.picpay.desafio.data.source.local.GetUsersLocalDataSource
import com.picpay.desafio.data.source.local.SaveUsersLocalDataSource
import com.picpay.desafio.data.stub.UserStub
import com.picpay.desafio.domain.entity.SuccessResult
import com.picpay.desafio.domain.exceptions.NoConnectionException
import com.picpay.desafio.domain.repository.GetUsersRepository
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetUsersRepositoryTest {
    private lateinit var repository: GetUsersRepository

    @MockK
    private lateinit var getUsersRemoteDataSource: GetUsersRemoteDataSource

    @MockK
    private lateinit var getUsersLocalDataSource: GetUsersLocalDataSource

    @MockK
    private lateinit var saveUsersLocalDataSource: SaveUsersLocalDataSource

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        repository = GetUsersRepositoryImpl(
            getUsersRemoteDataSource,
            getUsersLocalDataSource,
            saveUsersLocalDataSource
        )
    }

    @Test
    fun when_get_user_result_users_from_remote() = runBlockingTest {
        coEvery {
            getUsersRemoteDataSource.getUsers()
        } returns UserStub.getUserListSuccessResult()

        coEvery {
            saveUsersLocalDataSource.saveUsers(UserStub.getUserListSuccessResult().body)
        } returns Unit

        assertEquals(
            UserStub.getUserListSuccessResult(),
            repository.getUsers()
        )

        coVerify { getUsersRemoteDataSource.getUsers() }
    }

    @Test
    fun when_get_user_from_local() = runBlockingTest {
        coEvery {
            getUsersRemoteDataSource.getUsers()
        } throws NoConnectionException()

        coEvery {
            getUsersLocalDataSource.getUsers()
        } returns UserStub.getUserList()

        assertEquals(
            SuccessResult(UserStub.getUserList()),
            repository.getUsers() as SuccessResult
        )
    }
}
