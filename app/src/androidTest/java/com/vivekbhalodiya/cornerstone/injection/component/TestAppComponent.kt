/*
 * Created by Vivek Bhalodiya on 21/11/19 7:50 PM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 21/11/19 7:10 PM
 */

package com.vivekbhalodiya.cornerstone.injection.component

import com.vivekbhalodiya.cornerstone.MyTestApplication
import com.vivekbhalodiya.cornerstone.injection.module.ActivityBindingModule
import com.vivekbhalodiya.cornerstone.injection.module.FakeAppModule
import com.vivekbhalodiya.cornerstone.injection.module.FakeNetworkModule
import com.vivekbhalodiya.cornerstone.injection.module.FakeRepositoryModule
import com.vivekbhalodiya.cornerstone.injection.module.FragmentBindingModule
import com.vivekbhalodiya.cornerstone.injection.module.ViewModelFactoryModule
import com.vivekbhalodiya.cornerstone.ui.HomeActivityTest
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