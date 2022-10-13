package com.picpay.desafio.android.viewmodel

import com.picpay.desafio.android.getOrAwaitValue
import com.picpay.desafio.android.ui.viewmodel.ScreenState
import com.picpay.desafio.android.ui.viewmodel.UserViewModel
import com.picpay.desafio.domain.usecase.GetUserUseCase
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class UserViewModelTest : ViewModelBaseTest() {

    private lateinit var userViewModel: UserViewModel

    @MockK
    private lateinit var userUseCase: GetUserUseCase

    @Before
    fun setUp() {
        coEvery {
            userUseCase.getUser()
        } returns UserStub.getUserListSuccessResult()

        userViewModel = UserViewModel(userUseCase)
    }

    @Test
    fun when_on_start_result_success() {
        userViewModel.onStart()

        assert(userViewModel.viewState.state.getOrAwaitValue() is ScreenState.Success)

        assertEquals(
            UserStub.getUserListSuccessResult().body,
            (userViewModel.viewState.state.getOrAwaitValue() as ScreenState.Success).users
        )
    }

}
