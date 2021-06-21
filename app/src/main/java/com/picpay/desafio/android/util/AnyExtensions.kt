package com.picpay.desafio.android.util

inline fun <reified T> Any.cast() = takeIf { this is T }?.let { this as T }
    ?: throw ClassCastException()
