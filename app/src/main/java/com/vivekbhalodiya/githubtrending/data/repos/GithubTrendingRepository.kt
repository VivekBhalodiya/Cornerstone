/*
 * Created by Vivek Bhalodiya on 19/11/19 5:30 PM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 19/11/19 5:30 PM
 */

package com.vivekbhalodiya.githubtrending.data.repos

import com.vivekbhalodiya.githubtrending.data.model.GithubTrendingResponse
import com.vivekbhalodiya.githubtrending.data.source.local.GithubTrendingDao
import com.vivekbhalodiya.githubtrending.data.source.remote.ApiInterface
import com.vivekbhalodiya.githubtrending.utils.AppConstants.Companion.HOUR_IN_MILLISECONDS
import com.vivekbhalodiya.githubtrending.utils.AppConstants.Companion.TWO_HOURS_IN_MILLISECONDS
import com.vivekbhalodiya.githubtrending.utils.NetworkUtils
import com.vivekbhalodiya.githubtrending.utils.PrefsUtils
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Vivek Patel on 2019-11-19.
 */
class GithubTrendingRepository @Inject constructor(
    private val apiInterface: ApiInterface,
    private val githubTrendingDao: GithubTrendingDao,
    private val networkUtils: NetworkUtils,
    private val prefsUtils: PrefsUtils
) {
    fun getGithubTrendingRepos(): Observable<List<GithubTrendingResponse>> {
        var observablesFromApi: Observable<List<GithubTrendingResponse>>? = null

        if (networkUtils.isConnected()) {
            observablesFromApi = getGithubTrendingReposFromApi()
        }
        val observableFromDb: Observable<List<GithubTrendingResponse>> =
            getGithubTrendingReposFromDb()

        return when {
            networkUtils.isConnected() -> Observable.concatArrayEager(
                observablesFromApi,
                observableFromDb
            )
            isCacheExpired() -> {
                deleteGithubTrendingReposFromDb()
                Observable.just<List<GithubTrendingResponse>>(emptyList())
            }
            else -> observableFromDb
        }
    }

    private fun getGithubTrendingReposFromDb(): Observable<List<GithubTrendingResponse>> {
        return githubTrendingDao.queryGithubTrendingRepos()
    }

    private fun getGithubTrendingReposFromApi(): Observable<List<GithubTrendingResponse>> {
        return apiInterface.getTrendingRepositories()
            .map { response ->
                return@map if (isValidResponse(response)) {
                    deleteGithubTrendingReposFromDb()
                    insertResponseIntoDb(response.body()!!)
                    response.body()
                } else {
                    return@map Single.error<Exception>(Exception(response.errorBody().toString()))
                }
            }
    }

    private fun insertResponseIntoDb(githubTrendingResponseList: List<GithubTrendingResponse>) {
        for (item in githubTrendingResponseList) {
            githubTrendingDao.insertGithubTrendingRepos(item)
        }
        addCurrentTimeStamp()
    }

    private fun deleteGithubTrendingReposFromDb() {
        githubTrendingDao.deleteAll()
    }

    private fun isValidResponse(response: Response<List<GithubTrendingResponse>>) =
        response.isSuccessful && response.body() != null && response.body()!!.isNotEmpty()


    private fun addCurrentTimeStamp() {
        try {
            prefsUtils.freshDataTimeStamp = System.currentTimeMillis() * HOUR_IN_MILLISECONDS
        } catch (e: RuntimeException) {
            Timber.e(e)
        }
    }

    private fun isCacheExpired(): Boolean {
        var isCacheExpired = false
        try {
            isCacheExpired =
                prefsUtils.freshDataTimeStamp < System.currentTimeMillis() * TWO_HOURS_IN_MILLISECONDS
        } catch (e: RuntimeException) {
            Timber.e(e)
        }
        Timber.e("CacheExpired: %s", isCacheExpired)
        return isCacheExpired
    }
}