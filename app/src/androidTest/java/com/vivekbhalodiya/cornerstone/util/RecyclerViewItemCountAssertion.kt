/*
 * Created by Vivek Bhalodiya on 21/11/19 9:22 PM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 21/11/19 6:31 PM
 */

package com.vivekbhalodiya.cornerstone.util

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import org.hamcrest.Matcher
import org.junit.Assert.assertThat


class RecyclerViewItemCountAssertion(private val matcher: Matcher<Int>) : ViewAssertion {

    override fun check(view: View, noViewFoundException: NoMatchingViewException?) {
        if (noViewFoundException != null) {
            throw noViewFoundException
        }

        val recyclerView = view as RecyclerView
        val adapter = recyclerView.adapter
        assertThat(adapter!!.itemCount, matcher)
    }

}