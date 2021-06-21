package com.picpay.desafio.data.api

import com.picpay.desafio.data.mapper.Mapper
import com.picpay.desafio.data.model.error.ErrorRequestType
import com.picpay.desafio.data.model.error.ErrorRequestType.NO_CONTENT
import com.picpay.desafio.domain.entity.EmptyResult
import com.picpay.desafio.domain.entity.ErrorResult
import com.picpay.desafio.domain.entity.Result
import com.picpay.desafio.domain.entity.SuccessResult
import retrofit2.Response

private const val ZERO_VALUE = 0

fun <T, O> Response<T>.create(mapper: Mapper<T, O>): Result<O> {
    return ApiResponse.create(this).converter(mapper)
}

private fun <T, O> ApiResponse<T>.converter(mapper: Mapper<T, O>): Result<O> {
    return when (this) {
        is ApiEmptyResponse -> EmptyResult(
            ErrorRequestType.convert(code).name,
            message
        )
        is ApiSuccessResponse -> SuccessResult(mapper.transform(body))
        is ApiErrorResponse -> ErrorResult(code, error)
    }
}


sealed class ApiResponse<T> {
    companion object {
        fun <T> create(response: Response<T>): ApiResponse<T> {
            return if (response.isSuccessful) {
                val body = response.body()
                val emptyBody =
                    response.code() == NO_CONTENT.code || (body is List<*> && body.size == ZERO_VALUE)
                if (body == null || emptyBody)
                    ApiEmptyResponse(
                        response.code(),
                        response.message()
                    )
                else ApiSuccessResponse(body = body)
            } else {
                ApiErrorResponse(
                    response.code(),
                    response.errorBody()?.string().orEmpty()
                )
            }
        }
    }
}

data class ApiSuccessResponse<T>(
    val body: T
) : ApiResponse<T>()

class ApiEmptyResponse<T>(
    val code: Int,
    val message: String
) : ApiResponse<T>()

data class ApiErrorResponse<T>(
    val code: Int,
    val error: String
) : ApiResponse<T>()


