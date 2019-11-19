/*
 * Created by Vivek Bhalodiya on 19/11/19 2:44 PM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 19/11/19 2:44 PM
 */

package com.vivekbhalodiya.githubtrendingrepos.data.source.remote

import com.vivekbhalodiya.githubtrendingrepos.data.TrendingRepositoriesResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by Vivek Patel on 2019-11-19.
 */
interface ApiInterface {

    @GET("repositories") //Query parameters can be added later on.
    fun getTrendingRepositories(): Observable<Response<List<TrendingRepositoriesResponse>>>
}