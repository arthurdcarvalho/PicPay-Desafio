package com.picpay.desafio.data.source.remote

import com.picpay.desafio.data.mapper.UserResponseListToUserListMapper
import com.picpay.desafio.data.source.UserRemoteDataSource
import com.picpay.desafio.data.source.UserRemoteDataSourceImpl
import com.picpay.desafio.data.source.service.PicPayService
import com.picpay.desafio.data.stub.UserStub
import com.picpay.desafio.domain.entity.EmptyResult
import com.picpay.desafio.domain.entity.ErrorResult
import com.picpay.desafio.domain.entity.SuccessResult
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class UserRemoteDataSourceTest {

    private lateinit var userRemoteDataSource: UserRemoteDataSource

    @MockK
    private lateinit var picPayService: PicPayService

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        userRemoteDataSource =
            UserRemoteDataSourceImpl(picPayService, UserResponseListToUserListMapper())
    }

    @Test
    fun when_get_user_list_success_response() = runBlockingTest {
        coEvery {
            picPayService.getUsers()
        } returns UserStub.getUserListSuccessResponse()

        assertEquals(
            UserStub.getUserListSuccessResponse().body()?.first()?.name,
            (userRemoteDataSource.getUsers() as SuccessResult).body.first().name
        )
    }

    @Test
    fun when_get_user_list_empty_response() = runBlockingTest {
        coEvery {
            picPayService.getUsers()
        } returns UserStub.getUserListEmptyResponse()

        assertTrue(
            (userRemoteDataSource.getUsers() is EmptyResult)
        )

        coVerify { picPayService.getUsers() }
    }

    @Test
    fun when_get_user_list_error_response() = runBlockingTest {
        coEvery {
            picPayService.getUsers()
        } returns UserStub.getUserListErrorResponse()


        assertTrue(
            (userRemoteDataSource.getUsers() is ErrorResult)
        )

        coVerify { picPayService.getUsers() }
    }

}