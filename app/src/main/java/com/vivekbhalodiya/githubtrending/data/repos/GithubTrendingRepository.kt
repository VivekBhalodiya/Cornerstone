/*
 * Created by Vivek Bhalodiya on 19/11/19 5:30 PM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 19/11/19 5:30 PM
 */

package com.vivekbhalodiya.githubtrending.data.repos

import com.vivekbhalodiya.githubtrending.data.source.local.GithubTrendingDao
import com.vivekbhalodiya.githubtrending.data.source.remote.ApiInterface
import javax.inject.Inject

/**
 * Created by Vivek Patel on 2019-11-19.
 */
class GithubTrendingRepository @Inject constructor(
    val apiInterface: ApiInterface,
    val githubTrendingDao: GithubTrendingDao
) {
}