package com.picpay.desafio.android.viewmodel

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.picpay.desafio.android.di.AppComponent
import com.picpay.desafio.data.util.Network
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

@ExperimentalCoroutinesApi
abstract class ViewModelBaseTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @RelaxedMockK
    protected lateinit var context: Context

    @MockK
    protected lateinit var network: Network

    @Before
    fun mockitoInit() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(Dispatchers.Unconfined)

        startKoin {
            androidContext(context)
            modules(AppComponent.getAllModules())
        }

        every { network.hasInternetConnection() } returns true
    }

    @After
    fun setUpAfter() {
        stopKoin()
    }

}