package com.picpay.desafio.android.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import com.picpay.desafio.android.ui.viewmodel.base.BaseViewModel
import com.picpay.desafio.android.util.emit
import com.picpay.desafio.domain.entity.EmptyResult
import com.picpay.desafio.domain.entity.ErrorResult
import com.picpay.desafio.domain.entity.Result
import com.picpay.desafio.domain.entity.SuccessResult
import com.picpay.desafio.domain.entity.User
import com.picpay.desafio.domain.usecase.GetUserUseCase

class UserViewModel(
    private val useCase: GetUserUseCase
) : BaseViewModel() {

    val viewState = UserViewState(
        state = MutableLiveData(),
        isLoading = MutableLiveData()
    )

    init {
        onStart()
    }

    fun onStart() {
        launch(
            {
                useCase.getUser().handleGetUserResult()
            }, ::handleLoading, ::handleError
        )
    }

    private fun Result<List<User>>.handleGetUserResult() {
        when (this) {
            is SuccessResult -> viewState.state.emit(ScreenState.Success(body))
            is EmptyResult -> viewState.state.emit(ScreenState.Empty(message))
            else -> handleError((this as ErrorResult).error)
        }
    }

    private fun handleError(error: String) = viewState.state.emit(ScreenState.Error(error))

    private fun handleLoading(isLoading: Boolean) = viewState.isLoading.emit(isLoading)
}