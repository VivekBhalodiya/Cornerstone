/*
 * Created by Vivek Bhalodiya on 19/11/19 2:25 PM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 19/11/19 2:25 PM
 */

package com.vivekbhalodiya.githubtrending.data.source.local

import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.vivekbhalodiya.githubtrending.data.model.GithubTrendingResponse
import io.reactivex.Observable

/**
 * Created by Vivek Patel on 2019-11-19.
 */

@Dao
interface GithubTrendingDao {

    @Query("SELECT * FROM trendingrepos")
    fun getGithubTrendingRepos(): Observable<List<GithubTrendingResponse>>

    @RawQuery(observedEntities = [GithubTrendingResponse::class])
    fun queryGithubTrendingRepos(query: SupportSQLiteQuery): Observable<List<GithubTrendingResponse>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGithubTrendingRepos(githubTrendingRepo: GithubTrendingResponse)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllGithubTrendingRepos(githubTrendingRepos: List<GithubTrendingResponse>)

    @Query("DELETE FROM trendingrepos")
    fun deleteAll()
}