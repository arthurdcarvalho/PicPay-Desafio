package com.picpay.desafio.data.source.local

import com.picpay.desafio.data.mapper.Mapper
import com.picpay.desafio.data.model.local.UserData
import com.picpay.desafio.data.source.db.UserDao
import com.picpay.desafio.domain.entity.User

class GetUsersLocalDataSourceImpl(
    private val userDao: UserDao,
    private val userDataToUserMapper: Mapper<List<UserData>, List<User>>
) : GetUsersLocalDataSource {
    override suspend fun getUsers(): List<User> {
        return userDataToUserMapper.transform(userDao.getUserList())
    }
}
