/*
 * Created by Vivek Bhalodiya on 19/11/19 11:09 PM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 19/11/19 11:09 PM
 */

package com.vivekbhalodiya.githubtrending.injection.module

import com.vivekbhalodiya.githubtrending.injection.scope.ActivityScope
import com.vivekbhalodiya.githubtrending.injection.scope.FragmentScope
import com.vivekbhalodiya.githubtrending.ui.trending.TrendingReposFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.DaggerFragment

/**
 * Created by Vivek Patel on 2019-11-19.
 */
@Module
abstract class FragmentBindingModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [FragmentModule::class])
    internal abstract fun provideTrendingReposFragment(): TrendingReposFragment
}

@Module
abstract class FragmentModule<in T : DaggerFragment> {
    @Binds
    @ActivityScope
    internal abstract fun bindFragment(fragment: T): DaggerFragment
}