package com.picpay.desafio.android.di

import com.picpay.desafio.android.ui.viewmodel.UserViewModel
import com.picpay.desafio.domain.usecase.UserUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

object AppModule {
    val viewModelModule = module {
        factory {
            UserViewModel(
                get(
                    named(UserUseCase::class.java.name)
                )
            )
        }
    }
}