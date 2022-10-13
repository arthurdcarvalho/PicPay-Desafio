package com.picpay.desafio.data.source

import com.picpay.desafio.data.api.create
import com.picpay.desafio.data.mapper.UserResponseListToUserListMapper
import com.picpay.desafio.data.source.service.GetUsersService
import com.picpay.desafio.domain.entity.Result
import com.picpay.desafio.domain.entity.User

class GetUsersRemoteDataSourceImpl(
    private val service: GetUsersService,
    private val listMapperList: UserResponseListToUserListMapper
) : GetUsersRemoteDataSource {
    override suspend fun getUsers(): Result<List<User>> =
        service.getUsers().create(listMapperList)
}
