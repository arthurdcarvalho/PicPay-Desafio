package com.picpay.desafio.android.robot

import android.app.Activity
import android.content.Context
import androidx.test.core.app.ActivityScenario
import com.picpay.desafio.android.util.cast
import com.picpay.desafio.data.source.UserRemoteDataSource
import com.picpay.desafio.data.source.local.UserLocalDataSource
import org.koin.core.qualifier.named
import org.koin.test.KoinTest
import org.koin.test.inject

open class ArrangeRobot : KoinTest {
    val context: Context by inject()
}
