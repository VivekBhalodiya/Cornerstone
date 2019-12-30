/*
 * Created by Vivek Bhalodiya on 19/11/19 2:25 PM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 19/11/19 2:25 PM
 */

package com.vivekbhalodiya.cornerstone.data.source.local

import androidx.room.Dao

/**
 * Created by Vivek Patel on 2019-11-19.
 */

@Dao
interface CornerstoneDao {

  /* @Query("SELECT * FROM cornerstonetable")
   fun getGithubTrendingRepos(): Observable<List<String>>

   @RawQuery(observedEntities = [CornerstoneResponse::class])
   fun query(query: SupportSQLiteQuery): Observable<List<String>>

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   fun insert(fakeData: String)

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   fun insertAll(fakeData: List<String>)

   @Query("DELETE FROM cornerstonetable")
   fun deleteAll()*/
}