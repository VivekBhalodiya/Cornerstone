/*
 * Created by Vivek Bhalodiya on 19/11/19 2:38 PM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 19/11/19 2:38 PM
 */

package com.vivekbhalodiya.githubtrendingrepos.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vivekbhalodiya.githubtrendingrepos.data.TrendingRepositoriesResponse

/**
 * Created by Vivek Patel on 2019-11-19.
 */

@Database(entities = arrayOf(TrendingRepositoriesResponse::class), version = 1)
abstract class Database : RoomDatabase() {
    abstract fun trendingRepositoriesDao(): TrendingRepositoriesDao
}