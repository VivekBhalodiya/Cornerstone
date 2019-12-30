/*
 * Created by Vivek Bhalodiya on 19/11/19 4:38 PM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 19/11/19 4:23 PM
 */

package com.vivekbhalodiya.cornerstone.injection.component

import com.vivekbhalodiya.cornerstone.CornerstoneApp
import com.vivekbhalodiya.cornerstone.injection.module.ActivityBindingModule
import com.vivekbhalodiya.cornerstone.injection.module.AppModule
import com.vivekbhalodiya.cornerstone.injection.module.FragmentBindingModule
import com.vivekbhalodiya.cornerstone.injection.module.NetworkModule
import com.vivekbhalodiya.cornerstone.injection.module.RepositoryModule
import com.vivekbhalodiya.cornerstone.injection.module.ViewModelFactoryModule
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
interface AppComponent : AndroidInjector<CornerstoneApp> {
  @Component.Factory
  interface Factory : AndroidInjector.Factory<CornerstoneApp>
}