/*
 * Created by Vivek Bhalodiya on 21/11/19 6:45 PM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 21/11/19 6:45 PM
 */

package com.vivekbhalodiya.githubtrending

import com.vivekbhalodiya.githubtrending.injection.component.DaggerTestAppComponent
import com.vivekbhalodiya.githubtrending.injection.component.TestAppComponent
import dagger.android.DaggerApplication

/**
 * Created by Vivek Patel on 2019-11-21.
 */
class MyTestApplication : DaggerApplication() {
    private val component: TestAppComponent by lazy {
        DaggerTestAppComponent.factory().create(this) as TestAppComponent
    }

    override fun applicationInjector() = component

    fun provideComponent() = component
}