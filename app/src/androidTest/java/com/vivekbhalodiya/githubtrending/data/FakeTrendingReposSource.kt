/*
 * Created by Vivek Bhalodiya on 21/11/19 7:19 PM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 21/11/19 7:19 PM
 */

package com.vivekbhalodiya.githubtrending.data

import com.vivekbhalodiya.githubtrending.data.model.GithubTrendingResponse
import kotlin.random.Random

/**
 * Created by Vivek Patel on 2019-11-21.
 */
class FakeTrendingReposSource {
    companion object {
        fun getFakeTrendingRepos(size: Int): List<GithubTrendingResponse> {
            val trendingReposList = mutableListOf<GithubTrendingResponse>()

            for (i in 0..size) {
                trendingReposList.add(
                    GithubTrendingResponse(
                        id = Random.nextLong(),
                        author = "Author$i"
                    )
                )
            }

            return trendingReposList
        }
    }
}