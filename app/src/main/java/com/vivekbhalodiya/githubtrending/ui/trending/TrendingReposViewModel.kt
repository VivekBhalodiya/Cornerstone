/*
 * Created by Vivek Bhalodiya on 19/11/19 11:05 PM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 19/11/19 11:05 PM
 */

package com.vivekbhalodiya.githubtrending.ui.trending

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vivekbhalodiya.githubtrending.data.model.GithubTrendingResponse
import com.vivekbhalodiya.githubtrending.data.repos.GithubTrendingRepository
import com.vivekbhalodiya.githubtrending.ui.base.BaseViewModel
import com.vivekbhalodiya.githubtrending.utils.onBackground
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Vivek Patel on 2019-11-19.
 */
class TrendingReposViewModel @Inject constructor() : BaseViewModel() {
    @Inject
    lateinit var githubTrendingRepository: GithubTrendingRepository

    private val trendingRepositoriesResult: MutableLiveData<List<GithubTrendingResponse>> = MutableLiveData()

    fun trendingRepositoriesResult(): LiveData<List<GithubTrendingResponse>> = trendingRepositoriesResult

    fun test() {
        addDisposable(githubTrendingRepository.getGithubTrendingRepos()
            .onBackground()
            .subscribe({
               trendingRepositoriesResult.postValue(it)
            }, {
                Timber.e(it)
            }))
    }
}