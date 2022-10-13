package com.picpay.desafio.data.source.local

import com.picpay.desafio.data.mapper.UserDataListToUserListMapper
import com.picpay.desafio.data.mapper.UserListToUserDataListMapper
import com.picpay.desafio.data.source.db.UserDao
import com.picpay.desafio.data.stub.UserStub
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetUsersLocalDataSourceTest {

    private lateinit var getUsersLocalDataSource: GetUsersLocalDataSource

    @MockK
    private lateinit var userDao: UserDao

    private val userDataListToUserListMapper = UserDataListToUserListMapper()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getUsersLocalDataSource = GetUsersLocalDataSourceImpl(
            userDao,
            userDataListToUserListMapper
        )
    }

    @Test
    fun when_get_user_is_not_empty() = runBlockingTest {
        coEvery {
            userDao.getUserList()
        } returns UserStub.getUserDataList()

        assertEquals(
            userDataListToUserListMapper.transform(UserStub.getUserDataList()),
            getUsersLocalDataSource.getUsers()
        )
    }

    @Test
    fun when_get_user_is_empty() = runBlockingTest {
        coEvery {
            userDao.getUserList()
        } returns emptyList()

        assertTrue(
            getUsersLocalDataSource.getUsers().isEmpty()
        )
    }

}
