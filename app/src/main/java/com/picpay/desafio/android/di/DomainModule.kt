package com.picpay.desafio.android.di

import com.picpay.desafio.data.source.UserRemoteDataSource
import com.picpay.desafio.domain.repository.UserRepository
import com.picpay.desafio.data.repository.UserRepositoryImpl
import com.picpay.desafio.data.source.local.UserLocalDataSource
import com.picpay.desafio.domain.usecase.UserUseCase
import com.picpay.desafio.domain.usecase.UserUseCaseImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

object DomainModule {
    val useCaseModule = module {
        factory<UserUseCase>(
            named(UserUseCase::class.java.name)
        ) {
            UserUseCaseImpl(
                get(
                    named(UserRepository::class.java.name)
                )
            )
        }
    }

    val repositoryModule = module {
        factory<UserRepository>(
            named(UserRepository::class.java.name)
        ) {
            UserRepositoryImpl(
                get(
                    named(UserRemoteDataSource::class.java.name)
                ),
                get(
                    named(UserLocalDataSource::class.java.name)
                )
            )
        }
    }
}