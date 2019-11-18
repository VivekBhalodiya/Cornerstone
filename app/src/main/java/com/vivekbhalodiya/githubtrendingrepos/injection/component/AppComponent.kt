/*
 * Created by Vivek Bhalodiya on 19/11/19 12:35 AM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 19/11/19 12:35 AM
 */

package com.vivekbhalodiya.githubtrendingrepos.injection.component

import android.content.Context
import com.vivekbhalodiya.githubtrendingrepos.GithubTrendingReposApp
import com.vivekbhalodiya.githubtrendingrepos.injection.module.AppModule
import com.vivekbhalodiya.githubtrendingrepos.injection.module.ViewModelFactoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

/**
 * Created by Vivek Patel on 2019-11-19.
 */
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, ViewModelFactoryModule::class])
interface AppComponent : AndroidInjector<GithubTrendingReposApp> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }
}