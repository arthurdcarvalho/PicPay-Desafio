package com.picpay.desafio.data.source.local

import com.picpay.desafio.data.mapper.UserListToUserDataListMapper
import com.picpay.desafio.data.source.db.UserDao
import com.picpay.desafio.data.stub.UserStub
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class SaveUsersLocalDataSourceTest {

    private lateinit var saveUsersLocalDataSource: SaveUsersLocalDataSource

    @MockK(relaxed = true)
    private lateinit var userDao: UserDao

    private val userListToUserDataListMapper = UserListToUserDataListMapper()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        saveUsersLocalDataSource = SaveUsersLocalDataSourceImpl(
            userDao = userDao,
            userToUserDataMapper = userListToUserDataListMapper
        )
    }

    @Test
    fun when_save_user_list() = runBlockingTest {
        coEvery {
            userDao.getUserList()
        } returns UserStub.getUserDataList()

        saveUsersLocalDataSource.saveUsers(
            UserStub.getUserList()
        )

        coVerify {
            userDao.saveUserList(userListToUserDataListMapper.transform(UserStub.getUserList()))
        }
    }
}