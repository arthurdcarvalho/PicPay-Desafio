package com.picpay.desafio.data.mapper

import com.picpay.desafio.data.model.response.UserResponse
import com.picpay.desafio.domain.entity.User

class UserResponseListToUserListMapper : Mapper<List<UserResponse>, List<User>> {
    override fun transform(classIn: List<UserResponse>?): List<User> {
        return classIn?.map { response ->
            User(
                id = response.id ?: 0,
                name = response.name.orEmpty(),
                username = response.username.orEmpty(),
                img = response.img.orEmpty()
            )
        } ?: emptyList()
    }
}

