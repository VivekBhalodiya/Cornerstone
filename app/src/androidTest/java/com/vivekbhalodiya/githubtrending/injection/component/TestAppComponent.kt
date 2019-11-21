/*
 * Created by Vivek Bhalodiya on 21/11/19 7:50 PM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 21/11/19 7:10 PM
 */

package com.vivekbhalodiya.githubtrending.injection.component

import com.vivekbhalodiya.githubtrending.MyTestApplication
import com.vivekbhalodiya.githubtrending.injection.module.*
import com.vivekbhalodiya.githubtrending.ui.HomeActivityTest
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class, FakeAppModule::class, ViewModelFactoryModule::class,
        ActivityBindingModule::class, FakeNetworkModule::class, FakeRepositoryModule::class, FragmentBindingModule::class]
)
interface TestAppComponent : AndroidInjector<MyTestApplication> {
    fun inject(homeActivityTest: HomeActivityTest)

    @Component.Factory
    interface Factory : AndroidInjector.Factory<MyTestApplication>
}