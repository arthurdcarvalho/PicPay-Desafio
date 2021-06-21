package com.picpay.desafio.data.model.error

enum class ErrorRequestType(val code: Int) {
    NO_CONTENT(204),
    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    FORBIDDEN(403),
    NOT_FOUND(404),
    TOKEN_EXPIRED(409),
    INTERNAL_SERVER_ERROR(500),
    SERVICE_UNAVAILABLE(503);

    companion object {
        fun convert(code: Int?): ErrorRequestType {
            return try {
                enumValues<ErrorRequestType>().first { it.code == code }
            } catch (e: NoSuchElementException) {
                INTERNAL_SERVER_ERROR
            }
        }
    }
}
