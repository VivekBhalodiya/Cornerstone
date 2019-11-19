/*
 * Created by Vivek Bhalodiya on 19/11/19 5:31 PM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 19/11/19 5:31 PM
 */

package com.vivekbhalodiya.githubtrending.injection.module

import com.vivekbhalodiya.githubtrending.data.repos.GithubTrendingRepository
import com.vivekbhalodiya.githubtrending.data.source.local.GithubTrendingDao
import com.vivekbhalodiya.githubtrending.data.source.remote.ApiInterface
import com.vivekbhalodiya.githubtrending.utils.NetworkUtils
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Vivek Patel on 2019-11-19.
 */

@Module(includes = [NetworkModule::class, AppModule::class])
class RepositoryModule {

    @Provides
    @Singleton
    internal fun provideGithubTrendingRepository(
        apiInterface: ApiInterface,
        githubTrendingDao: GithubTrendingDao,
        networkUtils: NetworkUtils
    ) = GithubTrendingRepository(apiInterface, githubTrendingDao, networkUtils)
}