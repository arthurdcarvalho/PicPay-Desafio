package com.picpay.desafio.domain.entity

sealed class Result<T>
data class EmptyResult<T>(val error: String, val message: String) : Result<T>()
data class SuccessResult<T>(val body: T) : Result<T>()
data class ErrorResult<T>(val code: Int, val error: String) : Result<T>()