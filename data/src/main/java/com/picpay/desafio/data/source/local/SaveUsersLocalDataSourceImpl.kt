package com.picpay.desafio.data.source.local

import com.picpay.desafio.data.mapper.Mapper
import com.picpay.desafio.data.model.local.UserData
import com.picpay.desafio.data.source.db.UserDao
import com.picpay.desafio.domain.entity.User

class SaveUsersLocalDataSourceImpl(
    private val userDao: UserDao,
    private val userToUserDataMapper: Mapper<List<User>, List<UserData>>
) : SaveUsersLocalDataSource {
    override suspend fun saveUsers(users: List<User>) =
        userDao.saveUserList(userToUserDataMapper.transform(users))
}