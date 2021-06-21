package com.picpay.desafio.data.mapper

import com.picpay.desafio.data.model.response.UserResponse
import com.picpay.desafio.domain.entity.User
import org.junit.Assert.assertEquals
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString

class UserResponseListToUserListMapperTest : BaseMapperTest<List<UserResponse>, List<User>>(
    UserResponseListToUserListMapper::class.java.name
) {

    override fun classIn(): List<UserResponse> {
        return listOf(
            UserResponse(
                id = anyInt(),
                name = "",
                username = "",
                img = anyString()
            )
        )
    }

    override fun classOut(): List<User> {
        return listOf(
            User(
                id = anyInt(),
                name = "",
                username = "",
                img = anyString()
            )
        )
    }

    override fun mapClassNullInToClassNullOut() {
        assertEquals(emptyList<User>(), mapper.transform(null))
    }
}
