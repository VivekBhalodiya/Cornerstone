/*
 * Created by Vivek Bhalodiya on 21/11/19 7:50 PM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 21/11/19 7:10 PM
 */

package com.vivekbhalodiya.githubtrending.di.component

import com.vivekbhalodiya.githubtrending.GithubTrendingApp
import com.vivekbhalodiya.githubtrending.di.module.FakeAppModule
import com.vivekbhalodiya.githubtrending.di.module.FakeNetworkModule
import com.vivekbhalodiya.githubtrending.di.module.FakeRepositoryModule
import com.vivekbhalodiya.githubtrending.injection.module.ActivityBindingModule
import com.vivekbhalodiya.githubtrending.injection.module.ViewModelFactoryModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        FakeAppModule::class,
        AndroidSupportInjectionModule::class,
        ViewModelFactoryModule::class,
        ActivityBindingModule::class,
        FakeNetworkModule::class,
        FakeRepositoryModule::class
    ]
)
interface TestAppComponent : AndroidInjector<GithubTrendingApp> {

    @Component.Factory
    interface Factory : AndroidInjector.Factory<GithubTrendingApp>
}