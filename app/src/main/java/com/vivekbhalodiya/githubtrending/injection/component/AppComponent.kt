/*
 * Created by Vivek Bhalodiya on 19/11/19 4:38 PM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 19/11/19 4:23 PM
 */

package com.vivekbhalodiya.githubtrending.injection.component

import android.content.Context
import com.vivekbhalodiya.githubtrending.GithubTrendingApp
import com.vivekbhalodiya.githubtrending.injection.module.ActivityBindingModule
import com.vivekbhalodiya.githubtrending.injection.module.AppModule
import com.vivekbhalodiya.githubtrending.injection.module.NetworkModule
import com.vivekbhalodiya.githubtrending.injection.module.ViewModelFactoryModule
import com.vivekbhalodiya.githubtrending.injection.qualifiers.ApplicationContext
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by Vivek Patel on 2019-11-19.
 */
@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, ViewModelFactoryModule::class, ActivityBindingModule::class, NetworkModule::class])
interface AppComponent : AndroidInjector<GithubTrendingApp> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<GithubTrendingApp>() {
        @BindsInstance
        abstract fun appContext(@ApplicationContext context: Context)

        override fun seedInstance(instance: GithubTrendingApp?) {
            appContext(instance!!.applicationContext)
        }
    }
}