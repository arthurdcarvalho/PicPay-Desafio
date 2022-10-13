package com.picpay.desafio.android.main

import com.picpay.desafio.android.BaseTest
import com.picpay.desafio.android.R
import com.picpay.desafio.android.ui.MainActivity
import com.picpay.desafio.android.ui.viewmodel.UserViewModel
import com.picpay.desafio.domain.usecase.GetUserUseCase
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

class MainActivityTest : BaseTest() {

    private lateinit var userViewModel: UserViewModel

    @MockK
    private lateinit var userUseCase: GetUserUseCase

    @Before
    fun setUp() {
        userViewModel = UserViewModel(userUseCase)
        loadKoinModules(
            module {
                viewModel { UserViewModel(userUseCase) }
            }
        )
    }

    @Test
    fun shouldDisplayTitle() {
        arrange(userUseCase) {
            mockUsersList()
        }

        startActivity(MainActivity::class.java)

        assert {
            textIsVisible(
                context.getString(R.string.title)
            )
        }
    }

    @Test
    fun shouldDisplayListItem() {
        arrange(userUseCase) {
            mockUsersList()
        }

        startActivity(MainActivity::class.java)

        assert {
            textIsVisible(
                context.getString(R.string.title)
            )
            nameTextIs(
                0,
                "Sandrine"
            )
        }
    }
}