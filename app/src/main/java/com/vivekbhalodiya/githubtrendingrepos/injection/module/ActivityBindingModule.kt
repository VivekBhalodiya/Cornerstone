/*
 * Created by Vivek Bhalodiya on 19/11/19 1:44 AM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 19/11/19 1:44 AM
 */

package com.vivekbhalodiya.githubtrendingrepos.injection.module

import com.vivekbhalodiya.githubtrendingrepos.injection.scope.ActivityScope
import com.vivekbhalodiya.githubtrendingrepos.ui.home.HomeActivity
import com.vivekbhalodiya.githubtrendingrepos.ui.home.HomeActivityModule
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.DaggerAppCompatActivity

/**
 * Created by Vivek Patel on 2019-11-19.
 */

@Module
abstract class ActivityBindingModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = [HomeActivityModule::class])
    internal abstract fun bindHomeActivity(): HomeActivity
}


@Module(includes = [BaseActivityModule::class])
abstract class ActivityModule<in T : DaggerAppCompatActivity> {
    @Binds
    @ActivityScope
    internal abstract fun bindActivity(activity: T): DaggerAppCompatActivity
}

/**
 * Activity Binds Module
 *
 * Activity specific dependencies should be placed in BaseActivityModule.kt
 */

@Module
open class BaseActivityModule {

}