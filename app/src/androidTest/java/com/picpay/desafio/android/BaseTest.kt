package com.picpay.desafio.android

import android.app.Activity
import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import com.picpay.desafio.android.di.AppModule
import com.picpay.desafio.android.util.cast
import com.picpay.desafio.data.util.Network
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.koin.core.context.unloadKoinModules
import org.koin.test.KoinTest

open class BaseTest : KoinTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()


    protected lateinit var context: Context

    private lateinit var activityScenario: ActivityScenario<Activity>

    @Before
    fun initialization() {
        context = ApplicationProvider.getApplicationContext()
        MockKAnnotations.init(this)
        unloadKoinModules(AppModule.viewModelModule)
    }

    @After
    fun tearDown() {
        if (this::activityScenario.isInitialized) {
            activityScenario.close()
        }
    }

    protected fun <A : Activity> startActivity(activityClass: Class<A>) {
        activityScenario = ActivityScenario.launch(activityClass).cast()
    }

}
