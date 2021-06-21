package com.picpay.desafio.android.robot

import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.picpay.desafio.android.matchers.atPosition
import org.hamcrest.CoreMatchers.allOf

class AssertRobot {
    fun textIsVisible(
        text: String
    ) {
        onView(
            withText(text)
        ).check(
            matches(
                isDisplayed()
            )
        )
    }

    fun checkDisplayedViewAtRecyclerViewItem(@IdRes idList: Int, position: Int, @IdRes id: Int) {
        onView(withId(idList)).check(
            matches(
                atPosition(
                    position,
                    hasDescendant(
                        allOf(
                            withId(id),
                            isDisplayed()
                        )
                    )
                )
            )
        )
    }

    fun checkTextAtRecyclerViewItem(
        position: Int,
        @IdRes viewId: Int,
        text: String,
        @IdRes recyclerviewId: Int? = null
    ) {
        val viewMatcher = if (recyclerviewId != null) {
            allOf(
                withId(recyclerviewId),
                atPosition(
                    position,
                    hasDescendant(
                        allOf(
                            withId(viewId),
                            withText(text)
                        )
                    )
                ),
                isDisplayed()
            )
        } else {
            allOf(
                atPosition(
                    position,
                    hasDescendant(
                        allOf(
                            withId(viewId),
                            withText(text)
                        )
                    )
                ),
                isDisplayed()
            )
        }
        onView(viewMatcher).check(matches(isDisplayed()))
    }

}