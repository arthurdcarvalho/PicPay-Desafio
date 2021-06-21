package com.picpay.desafio.data.mapper

import com.picpay.desafio.data.model.local.UserData
import com.picpay.desafio.domain.entity.User

class UserListToUserDataListMapper : Mapper<List<User>, List<UserData>> {
    override fun transform(classIn: List<User>?): List<UserData> {
        return classIn?.map { user ->
            UserData(
                id = user.id,
                name = user.name,
                username = user.username,
                img = user.img
            )
        } ?: emptyList()
    }
}