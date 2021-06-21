package com.picpay.desafio.android.ui.viewmodel

import androidx.lifecycle.LiveData
import com.picpay.desafio.domain.entity.*

class UserViewState(
    val state: LiveData<ScreenState>,
    val isLoading: LiveData<Boolean>
)

sealed class ScreenState {
    data class Success(val users: List<User>) : ScreenState()
    data class Error(val error: String, val message: String? = null) : ScreenState()
    data class Empty(val error: String) : ScreenState()
    object NoConnection : ScreenState()
}
