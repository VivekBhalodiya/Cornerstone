/*
 * Created by Vivek Bhalodiya on 19/11/19 6:12 PM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 19/11/19 6:11 PM
 */

package com.vivekbhalodiya.githubtrending.injection.module

import androidx.fragment.app.FragmentManager
import com.vivekbhalodiya.githubtrending.injection.scope.ActivityScope
import com.vivekbhalodiya.githubtrending.ui.home.HomeActivity
import com.vivekbhalodiya.githubtrending.ui.home.HomeActivityModule
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.android.support.DaggerAppCompatActivity

/**
 * Created by Vivek Patel on 2019-11-19.
 */

@Module
abstract class ActivityBindingModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = [HomeActivityModule::class, FragmentBindingModule::class])
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
    @Provides
    @ActivityScope
    fun provideFragmentManager(activity: DaggerAppCompatActivity): FragmentManager =
        activity.supportFragmentManager
}