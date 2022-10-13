package com.picpay.desafio.android.robot

import android.content.Context
import org.koin.test.KoinTest
import org.koin.test.inject

open class ArrangeRobot : KoinTest {
    val context: Context by inject()
}
