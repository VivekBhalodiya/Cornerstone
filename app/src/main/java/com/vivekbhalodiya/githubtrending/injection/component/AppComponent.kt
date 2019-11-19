/*
 * Created by Vivek Bhalodiya on 19/11/19 4:38 PM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 19/11/19 4:23 PM
 */

package com.vivekbhalodiya.githubtrending.injection.component

import com.vivekbhalodiya.githubtrending.GithubTrendingApp
import com.vivekbhalodiya.githubtrending.injection.module.*
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by Vivek Patel on 2019-11-19.
 */
@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class, AppModule::class, ViewModelFactoryModule::class,
        ActivityBindingModule::class, NetworkModule::class, RepositoryModule::class, FragmentBindingModule::class]
)
interface AppComponent : AndroidInjector<GithubTrendingApp> {
    @Component.Factory
    interface Factory : AndroidInjector.Factory<GithubTrendingApp>
}