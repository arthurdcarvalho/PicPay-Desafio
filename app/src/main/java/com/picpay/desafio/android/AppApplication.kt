package com.picpay.desafio.android

import androidx.multidex.MultiDexApplication
import com.picpay.desafio.android.di.AppComponent
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.KoinComponent
import org.koin.core.context.startKoin

class AppApplication : MultiDexApplication(), KoinComponent {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@AppApplication)
            modules(AppComponent.getAllModules())
        }
    }
}