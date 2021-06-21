package com.picpay.desafio.android.matchers

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher

fun atPosition(position: Int, itemViewMatcher: Matcher<View>): Matcher<View> {

    return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {

        override fun describeTo(description: Description?) {
            description?.appendText("at $position")
        }

        override fun matchesSafely(view: RecyclerView): Boolean {
            val viewHolder = view.findViewHolderForAdapterPosition(position)
            return viewHolder != null && itemViewMatcher.matches(viewHolder.itemView)
        }
    }
}