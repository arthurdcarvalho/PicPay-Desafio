package com.picpay.desafio.android.di

import com.picpay.desafio.android.di.AppModule.viewModelModule
import com.picpay.desafio.android.di.DataModule.dataUtilsModule
import com.picpay.desafio.android.di.DataModule.localModule
import com.picpay.desafio.android.di.DataModule.remoteModule
import com.picpay.desafio.android.di.DataModule.serviceModule
import com.picpay.desafio.android.di.DomainModule.repositoryModule
import com.picpay.desafio.android.di.DomainModule.useCaseModule
import com.picpay.desafio.data.di.MapperModules.mapperModules

object AppComponent {
    fun getAllModules() = listOf(
        viewModelModule,
        dataUtilsModule,
        serviceModule,
        repositoryModule,
        remoteModule,
        localModule,
        mapperModules,
        useCaseModule
    )
}

