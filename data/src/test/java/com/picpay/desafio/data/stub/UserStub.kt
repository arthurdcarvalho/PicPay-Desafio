package com.picpay.desafio.data.stub

import com.picpay.desafio.data.model.local.UserData
import com.picpay.desafio.data.model.response.UserResponse
import com.picpay.desafio.domain.entity.SuccessResult
import com.picpay.desafio.domain.entity.User
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.mockito.ArgumentMatchers.anyString
import retrofit2.Response
import com.picpay.desafio.data.model.error.ErrorRequestType.SERVICE_UNAVAILABLE

object UserStub {
    fun getUserListSuccessResult(): SuccessResult<List<User>> {
        return SuccessResult(
            listOf(
                user(),
                user()
            )
        )
    }

    fun getUserList(): List<User> {
        return listOf(
            user()
        )
    }

    fun getUserDataList(): List<UserData> = listOf(
        userData()
    )

    private fun user() = User(
        id = 1,
        name = "Sandrine",
        username = "Tod86",
        img = "https://randomuser.me/api/portraits/men/1.jpg"
    )

    private fun userResponse() = UserResponse(
        id = 1,
        name = "Sandrine",
        username = "Tod86",
        img = "https://randomuser.me/api/portraits/men/1.jpg"
    )

    private fun userData() = UserData(
        id = 1,
        name = "Sandrine",
        username = "Tod86",
        img = "https://randomuser.me/api/portraits/men/1.jpg"
    )


    fun getUserListSuccessResponse() = Response.success(
        listOf(
            userResponse()
        )
    )

    fun getUserListEmptyResponse() = Response.success<List<UserResponse>>(
        null
    )

    fun getUserListErrorResponse() = Response.error<List<UserResponse>>(
        SERVICE_UNAVAILABLE.code,
        ResponseBody.create(
            MediaType.parse(anyString()), anyString()
        )
    )

}