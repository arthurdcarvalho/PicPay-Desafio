package com.picpay.desafio.data.source.local

import com.picpay.desafio.data.mapper.UserDataListToUserListMapper
import com.picpay.desafio.data.mapper.UserListToUserDataListMapper
import com.picpay.desafio.data.source.db.UserDao
import com.picpay.desafio.data.stub.UserStub
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
class UserLocalDataSourceTest {

    private lateinit var userLocalDataSource: UserLocalDataSource

    @MockK
    private lateinit var userDao: UserDao

    private val userDataListToUserListMapper = UserDataListToUserListMapper()
    private val userListToUserDataListMapper = UserListToUserDataListMapper()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        userLocalDataSource = UserLocalDataSourceImpl(
            userDao,
            userDataListToUserListMapper,
            userListToUserDataListMapper
        )
    }

    @Test
    fun when_get_user_is_not_empty() = runBlockingTest {
        coEvery {
            userDao.getUserList()
        } returns UserStub.getUserDataList()

        assertEquals(
            userDataListToUserListMapper.transform(UserStub.getUserDataList()),
            userLocalDataSource.getUsers()
        )
    }

    @Test
    fun when_get_user_is_empty() = runBlockingTest {
        coEvery {
            userDao.getUserList()
        } returns emptyList()

        assertTrue(
            userLocalDataSource.getUsers().isEmpty()
        )
    }

    @Test
    fun when_save_user_list() = runBlockingTest {
        coEvery {
            userDao.getUserList()
        } returns UserStub.getUserDataList()

        coEvery {
            userLocalDataSource.saveUsers(UserStub.getUserList())
        }

        assertTrue(
            userLocalDataSource.getUsers().isNotEmpty()
        )
    }

}
