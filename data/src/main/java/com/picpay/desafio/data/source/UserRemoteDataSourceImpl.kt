package com.picpay.desafio.data.source

import com.picpay.desafio.data.api.create
import com.picpay.desafio.data.mapper.UserResponseListToUserListMapper
import com.picpay.desafio.data.source.service.PicPayService
import com.picpay.desafio.domain.entity.Result
import com.picpay.desafio.domain.entity.User

class UserRemoteDataSourceImpl(
    private val service: PicPayService,
    private val listMapperList: UserResponseListToUserListMapper
) : UserRemoteDataSource {
    override suspend fun getUsers(): Result<List<User>> {
        return service.getUsers().create(listMapperList)
    }
}