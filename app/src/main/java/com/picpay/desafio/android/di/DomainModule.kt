package com.picpay.desafio.android.di

import com.picpay.desafio.data.source.GetUsersRemoteDataSource
import com.picpay.desafio.domain.repository.GetUsersRepository
import com.picpay.desafio.data.repository.GetUsersRepositoryImpl
import com.picpay.desafio.data.source.local.GetUsersLocalDataSource
import com.picpay.desafio.data.source.local.SaveUsersLocalDataSource
import com.picpay.desafio.domain.usecase.GetUserUseCase
import com.picpay.desafio.domain.usecase.GetUserUseCaseImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

object DomainModule {
    val useCaseModule = module {
        factory<GetUserUseCase>(
            named(GetUserUseCase::class.java.name)
        ) {
            GetUserUseCaseImpl(
                get(
                    named(GetUsersRepository::class.java.name)
                )
            )
        }
    }

    val repositoryModule = module {
        factory<GetUsersRepository>(
            named(GetUsersRepository::class.java.name)
        ) {
            GetUsersRepositoryImpl(
                get(
                    named(GetUsersRemoteDataSource::class.java.name)
                ),
                get(
                    named(GetUsersLocalDataSource::class.java.name)
                ),
                get(
                    named(SaveUsersLocalDataSource::class.java.name)
                )
            )
        }
    }
}