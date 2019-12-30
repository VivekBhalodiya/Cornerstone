/*
 * Created by Vivek Bhalodiya on 21/11/19 8:52 PM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 21/11/19 8:52 PM
 */

package com.vivekbhalodiya.cornerstone.ui

import android.os.SystemClock
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.vivekbhalodiya.cornerstone.BaseTest
import com.vivekbhalodiya.cornerstone.MyTestApplication
import com.vivekbhalodiya.cornerstone.R
import com.vivekbhalodiya.cornerstone.data.repos.CornerstoneRepository
import com.vivekbhalodiya.cornerstone.ui.home.HomeActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

/**
 * Created by Vivek Patel on 2019-11-21.
 */
@LargeTest
@RunWith(AndroidJUnit4::class)
class HomeActivityTest : BaseTest() {
    override fun injectDagger(application: MyTestApplication) {
        application.provideComponent().inject(this)
    }

    @Inject
    lateinit var githubTrendingRepository: CornerstoneRepository

    @get:Rule
    val activityTest = ActivityTestRule(HomeActivity::class.java, false, true)

    @Test
    fun allTrendingReposActivityTest() {
        SystemClock.sleep(5000)

        //Check if Error State is not being showed
        onView(withId(R.id.button_retry)).check(ViewAssertions.doesNotExist())
    }
}