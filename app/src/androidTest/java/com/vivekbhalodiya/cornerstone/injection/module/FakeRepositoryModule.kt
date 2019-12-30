/*
 * Created by Vivek Bhalodiya on 21/11/19 7:49 PM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 19/11/19 10:23 PM
 */

package com.vivekbhalodiya.cornerstone.injection.module

import dagger.Module

/**
 * Created by Vivek Patel on 2019-11-19.
 */

@Module(includes = [FakeNetworkModule::class, FakeAppModule::class])
class FakeRepositoryModule {

  /*@Provides
  @Singleton
  internal fun provideRepository(
      apiInterface: ApiInterface,
      cornerstoneDao: CornerstoneDao,
      networkUtils: NetworkUtils,
      prefsUtils: PrefsUtils
  ) = CornerstoneRepository(apiInterface, cornerstoneDao, networkUtils,prefsUtils)*/
}