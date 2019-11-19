/*
 * Created by Vivek Bhalodiya on 19/11/19 1:26 AM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 19/11/19 1:26 AM
 */

package com.vivekbhalodiya.githubtrending.ui.home

import com.vivekbhalodiya.githubtrending.data.repos.GithubTrendingRepository
import com.vivekbhalodiya.githubtrending.ui.base.BaseViewModel
import com.vivekbhalodiya.githubtrending.utils.onBackground
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Vivek Patel on 2019-11-19.
 */
class HomeViewModel @Inject constructor() : BaseViewModel() {
    @Inject
    lateinit var githubTrendingRepository: GithubTrendingRepository

    fun test() {
        githubTrendingRepository.getGithubTrendingRepos()
            .onBackground()
            .subscribe({},{
                Timber.e(it)
            })
    }
}