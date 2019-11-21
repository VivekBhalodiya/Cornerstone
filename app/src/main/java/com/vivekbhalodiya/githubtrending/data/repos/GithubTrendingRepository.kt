/*
 * Created by Vivek Bhalodiya on 19/11/19 5:30 PM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 19/11/19 5:30 PM
 */

package com.vivekbhalodiya.githubtrending.data.repos

import androidx.sqlite.db.SimpleSQLiteQuery
import com.vivekbhalodiya.githubtrending.data.model.GithubTrendingResponse
import com.vivekbhalodiya.githubtrending.data.source.local.GithubTrendingDao
import com.vivekbhalodiya.githubtrending.data.source.remote.ApiInterface
import com.vivekbhalodiya.githubtrending.utils.AppConstants.Companion.HOUR_IN_MILLISECONDS
import com.vivekbhalodiya.githubtrending.utils.AppConstants.Companion.TWO_HOURS_IN_MILLISECONDS
import com.vivekbhalodiya.githubtrending.utils.NetworkUtils
import com.vivekbhalodiya.githubtrending.utils.PrefsUtils
import com.vivekbhalodiya.githubtrending.utils.TrendingReposOrderBy
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
    fun getGithubTrendingRepos(
        orderBy: TrendingReposOrderBy = TrendingReposOrderBy.DEFAULT,
        orderType: String = ""
    ): Observable<List<GithubTrendingResponse>> {
        var observablesFromApi: Observable<List<GithubTrendingResponse>>? = null

        if (networkUtils.isConnected()) {
            observablesFromApi = getGithubTrendingReposFromApi()
        }
        val observableFromDb: Observable<List<GithubTrendingResponse>> =
            getGithubTrendingReposFromDb(orderBy, orderType)

        return when {
            //If order by is NOT requested
            networkUtils.isConnected() && orderBy == TrendingReposOrderBy.DEFAULT-> Observable.concatArrayEager(
                observablesFromApi,
                observableFromDb
            )
            //If order by is requested and isOnline, sort from database.
            networkUtils.isConnected() -> observableFromDb
            //Cache is expired and is NOT connected to internet, show error state.
            isCacheExpired() -> {
                deleteGithubTrendingReposFromDb()
                Observable.just<List<GithubTrendingResponse>>(emptyList())
            }
            else -> observableFromDb
        }
    }

    private fun getGithubTrendingReposFromDb(
        orderBy: TrendingReposOrderBy,
        orderType: String
    ): Observable<List<GithubTrendingResponse>> {
        return if ((orderBy == TrendingReposOrderBy.DEFAULT).not()) {
            githubTrendingDao.queryGithubTrendingRepos(getQueryToExecute(orderBy, orderType))
        } else {
            githubTrendingDao.getGithubTrendingRepos()
        }
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

    private fun getQueryToExecute(
        orderBy: TrendingReposOrderBy,
        orderType: String
    ): SimpleSQLiteQuery {
        return SimpleSQLiteQuery("SELECT * FROM trendingrepos ORDER BY " + orderBy.value + " " + orderType)
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