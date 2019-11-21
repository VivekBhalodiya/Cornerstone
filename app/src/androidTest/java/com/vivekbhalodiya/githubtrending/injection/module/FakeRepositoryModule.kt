/*
 * Created by Vivek Bhalodiya on 21/11/19 7:49 PM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 19/11/19 10:23 PM
 */

package com.vivekbhalodiya.githubtrending.injection.module

import com.vivekbhalodiya.githubtrending.data.repos.GithubTrendingRepository
import com.vivekbhalodiya.githubtrending.data.source.local.GithubTrendingDao
import com.vivekbhalodiya.githubtrending.data.source.remote.ApiInterface
import com.vivekbhalodiya.githubtrending.utils.NetworkUtils
import com.vivekbhalodiya.githubtrending.utils.PrefsUtils
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Vivek Patel on 2019-11-19.
 */

@Module(includes = [FakeNetworkModule::class, FakeAppModule::class])
class FakeRepositoryModule {

    @Provides
    @Singleton
    internal fun provideGithubTrendingRepository(
        apiInterface: ApiInterface,
        githubTrendingDao: GithubTrendingDao,
        networkUtils: NetworkUtils,
        prefsUtils: PrefsUtils
    ) = GithubTrendingRepository(apiInterface, githubTrendingDao, networkUtils,prefsUtils)
}