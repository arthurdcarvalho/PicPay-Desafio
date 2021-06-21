package com.picpay.desafio.data.source.local

import com.picpay.desafio.data.mapper.Mapper
import com.picpay.desafio.data.model.local.UserData
import com.picpay.desafio.data.source.db.UserDao
import com.picpay.desafio.domain.entity.User

class UserLocalDataSourceImpl(
    private val userDao: UserDao,
    private val userDataToUserMapper: Mapper<List<UserData>, List<User>>,
    private val userToUserDataMapper: Mapper<List<User>, List<UserData>>
) : UserLocalDataSource {
    override suspend fun getUsers(): List<User> {
        return userDataToUserMapper.transform(userDao.getUserList())
    }

    override suspend fun saveUsers(users: List<User>) {
        userDao.saveUserList(userToUserDataMapper.transform(users))
    }
}
