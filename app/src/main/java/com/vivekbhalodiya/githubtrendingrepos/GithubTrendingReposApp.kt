/*
 * Created by Vivek Bhalodiya on 18/11/19 11:46 PM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 18/11/19 11:46 PM
 */

package com.vivekbhalodiya.githubtrendingrepos

import com.vivekbhalodiya.githubtrendingrepos.injection.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber

/**
 * Created by Vivek Patel on 2019-11-18.
 */
class GithubTrendingReposApp : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent
            .factory()
            .create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
        setupTimber()
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}