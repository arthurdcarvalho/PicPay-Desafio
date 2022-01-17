package com.picpay.desafio.domain.exceptions

import java.io.IOException

class NoConnectionException: IOException() {
    override val message: String get() = MESSAGE

    companion object {
        private const val MESSAGE = "No Internet Connection"
    }
}