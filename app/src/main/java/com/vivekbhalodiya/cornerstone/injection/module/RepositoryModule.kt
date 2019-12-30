/*
 * Created by Vivek Bhalodiya on 19/11/19 5:31 PM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 19/11/19 5:31 PM
 */

package com.vivekbhalodiya.cornerstone.injection.module

import dagger.Module

/**
 * Created by Vivek Patel on 2019-11-19.
 */

@Module(includes = [NetworkModule::class, AppModule::class])
class RepositoryModule {

  /*@Provides
  @Singleton
  internal fun provideRepository(
    apiInterface: ApiInterface,
    cornerstoneDao: CornerstoneDao,
    networkUtils: NetworkUtils,
    prefsUtils: PrefsUtils
  ) = CornerstoneRepository(apiInterface, cornerstoneDao, networkUtils, prefsUtils)*/
}