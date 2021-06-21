package com.picpay.desafio.data.di

import com.picpay.desafio.data.mapper.Mapper
import com.picpay.desafio.data.mapper.UserDataListToUserListMapper
import com.picpay.desafio.data.mapper.UserListToUserDataListMapper
import com.picpay.desafio.data.mapper.UserResponseListToUserListMapper
import com.picpay.desafio.data.model.local.UserData
import com.picpay.desafio.data.model.response.UserResponse
import com.picpay.desafio.domain.entity.User
import org.koin.core.qualifier.named
import org.koin.dsl.module

object MapperModules {
    val mapperModules = module {
        factory<Mapper<List<UserResponse>, List<User>>>(
            named(UserResponseListToUserListMapper::class.java.name)
        ) {
            UserResponseListToUserListMapper()
        }

        factory<Mapper<List<UserData>, List<User>>>(
            named(UserDataListToUserListMapper::class.java.name)
        ) {
            UserDataListToUserListMapper()
        }

        factory<Mapper<List<User>, List<UserData>>>(
            named(UserListToUserDataListMapper::class.java.name)
        ) {
            UserListToUserDataListMapper()
        }
    }
}
