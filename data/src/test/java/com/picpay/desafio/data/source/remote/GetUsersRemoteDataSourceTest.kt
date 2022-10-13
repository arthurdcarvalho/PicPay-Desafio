package com.picpay.desafio.data.source.remote

import com.picpay.desafio.data.mapper.UserResponseListToUserListMapper
import com.picpay.desafio.data.source.GetUsersRemoteDataSource
import com.picpay.desafio.data.source.GetUsersRemoteDataSourceImpl
import com.picpay.desafio.data.source.service.GetUsersService
import com.picpay.desafio.data.stub.UserStub
import com.picpay.desafio.domain.entity.EmptyResult
import com.picpay.desafio.domain.entity.ErrorResult
import com.picpay.desafio.domain.entity.SuccessResult
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetUsersRemoteDataSourceTest {

    private lateinit var getUsersRemoteDataSource: GetUsersRemoteDataSource

    @MockK
    private lateinit var getUsersService: GetUsersService

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getUsersRemoteDataSource =
            GetUsersRemoteDataSourceImpl(getUsersService, UserResponseListToUserListMapper())
    }

    @Test
    fun when_get_user_list_success_response() = runBlockingTest {
        coEvery {
            getUsersService.getUsers()
        } returns UserStub.getUserListSuccessResponse()

        assertEquals(
            UserStub.getUserListSuccessResponse().body()?.first()?.name,
            (getUsersRemoteDataSource.getUsers() as SuccessResult).body.first().name
        )
    }

    @Test
    fun when_get_user_list_empty_response() = runBlockingTest {
        coEvery {
            getUsersService.getUsers()
        } returns UserStub.getUserListEmptyResponse()

        assertTrue(
            (getUsersRemoteDataSource.getUsers() is EmptyResult)
        )

        coVerify { getUsersService.getUsers() }
    }

    @Test
    fun when_get_user_list_error_response() = runBlockingTest {
        coEvery {
            getUsersService.getUsers()
        } returns UserStub.getUserListErrorResponse()


        assertTrue(
            (getUsersRemoteDataSource.getUsers() is ErrorResult)
        )

        coVerify { getUsersService.getUsers() }
    }
}
