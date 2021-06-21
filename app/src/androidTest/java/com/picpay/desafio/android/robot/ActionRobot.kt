package com.picpay.desafio.android.robot

import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.CoreMatchers.allOf

class ActionRobot {
    fun clickOnView(@IdRes buttonId: Int) {
        onView(
            allOf(
                withId(buttonId),
                isDisplayed()
            )
        ).perform(click())
    }
}