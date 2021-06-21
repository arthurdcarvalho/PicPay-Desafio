package com.picpay.desafio.data.mapper

import com.picpay.desafio.data.model.local.UserData
import com.picpay.desafio.domain.entity.User

class UserDataListToUserListMapper : Mapper<List<UserData>, List<User>> {
    override fun transform(classIn: List<UserData>?): List<User> {
        return classIn?.map { userData ->
            User(
                id = userData.id,
                name = userData.name,
                username = userData.username,
                img = userData.img
            )
        } ?: emptyList()
    }
}
