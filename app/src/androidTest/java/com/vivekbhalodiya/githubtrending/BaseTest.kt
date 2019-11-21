/*
 * Created by Vivek Bhalodiya on 21/11/19 7:46 PM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 21/11/19 7:10 PM
 */

package com.vivekbhalodiya.githubtrending

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Before
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
abstract class BaseTest {
    lateinit var application: MyTestApplication

    @Before
    fun setup() {
        application = InstrumentationRegistry.getInstrumentation()
            .targetContext.applicationContext as MyTestApplication
        injectDagger(application)
    }

    abstract fun injectDagger(application: MyTestApplication)
}