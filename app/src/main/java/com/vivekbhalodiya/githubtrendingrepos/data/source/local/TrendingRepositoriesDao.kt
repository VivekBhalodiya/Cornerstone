/*
 * Created by Vivek Bhalodiya on 19/11/19 2:25 PM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 19/11/19 2:25 PM
 */

package com.vivekbhalodiya.githubtrendingrepos.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vivekbhalodiya.githubtrendingrepos.data.TrendingRepositoriesResponse
import io.reactivex.Single

/**
 * Created by Vivek Patel on 2019-11-19.
 */

@Dao
interface TrendingRepositoriesDao {

    @Query("SELECT * FROM trendingrepos")
    fun queryTrendingRepositories(): Single<List<TrendingRepositoriesResponse>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllTrendingRepositories(trendingRepositories: List<TrendingRepositoriesResponse>)
}