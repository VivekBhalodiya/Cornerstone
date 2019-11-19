/*
 * Created by Vivek Bhalodiya on 19/11/19 5:30 PM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 19/11/19 5:30 PM
 */

package com.vivekbhalodiya.githubtrending.data.repos

import com.vivekbhalodiya.githubtrending.data.GithubTrendingResponse
import com.vivekbhalodiya.githubtrending.data.source.local.GithubTrendingDao
import com.vivekbhalodiya.githubtrending.data.source.remote.ApiInterface
import com.vivekbhalodiya.githubtrending.utils.NetworkUtils
import io.reactivex.Observable
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Vivek Patel on 2019-11-19.
 */
class GithubTrendingRepository @Inject constructor(
    val apiInterface: ApiInterface,
    val githubTrendingDao: GithubTrendingDao,
    val networkUtils: NetworkUtils
) {
    fun getGihubTrendingRepos(): Observable<List<GithubTrendingResponse>> {
        var observablesFromApi: Observable<List<GithubTrendingResponse>>? = null

        if (networkUtils.isConnected()) {
            observablesFromApi = getGithubTrendingReposFromApi()
        }
        val observableFromDb = getGithubTrendingReposFromDb()

        return if (networkUtils.isConnected()) {
            Observable.concatArrayEager(
                observablesFromApi,
                observableFromDb
            )
        } else {
            observableFromDb
        }
    }

    private fun getGithubTrendingReposFromDb(): Observable<List<GithubTrendingResponse>> {
        return githubTrendingDao.queryGithubTrendingRepos()
            .doOnNext {
                Timber.d("OfflineTrendingRepos: %s", it)
            }
    }

    private fun getGithubTrendingReposFromApi(): Observable<List<GithubTrendingResponse>> {
        return apiInterface.getTrendingRepositories()
            .map {
                return@map if (it.isSuccessful && it.body() != null && it.body()!!.isNotEmpty()) {
                    Timber.d("TrendingRepos: %s", it.body())
                    for (item in it.body()!!){
                        githubTrendingDao.insertGihubtrendingRepository(item)
                    }
                    it.body()
                } else {
                    emptyList()
                }
            }
    }
}