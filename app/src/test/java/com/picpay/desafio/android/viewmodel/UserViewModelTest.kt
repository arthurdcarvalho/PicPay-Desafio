package com.picpay.desafio.android.viewmodel

import com.picpay.desafio.android.getOrAwaitValue
import com.picpay.desafio.android.ui.viewmodel.ScreenState
import com.picpay.desafio.android.ui.viewmodel.UserViewModel
import com.picpay.desafio.domain.usecase.UserUseCase
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
    private lateinit var userUseCase: UserUseCase

    @Before
    fun setUp() {
        userViewModel = UserViewModel(userUseCase, network)
    }

    @Test
    fun when_on_resume_result_success() {
        coEvery {
            userUseCase.getUser()

        } returns UserStub.getUserListSuccessResult()

        userViewModel.onResume()

        assert(userViewModel.viewState.state.getOrAwaitValue() is ScreenState.Success)

        assertEquals(
            UserStub.getUserListSuccessResult().body,
            (userViewModel.viewState.state.getOrAwaitValue() as ScreenState.Success).users
        )
    }

}
