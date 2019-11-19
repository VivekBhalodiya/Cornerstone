/*
 * Created by Vivek Bhalodiya on 19/11/19 4:30 PM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 19/11/19 4:23 PM
 */

package com.vivekbhalodiya.githubtrending.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created by Vivek Patel on 2019-11-19.
 */

@Entity(
    tableName = "trendingrepos"
)
data class GithubTrendingResponse(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long,

    @SerializedName("author")
    @ColumnInfo(name = "author")
    val author: String = "",

    @SerializedName("name")
    @ColumnInfo(name = "name")
    val name: String = "",

    @SerializedName("avatar")
    @ColumnInfo(name = "avatar")
    val avatar: String = "",

    @SerializedName("url")
    @ColumnInfo(name = "url")
    val url: String = "",

    @SerializedName("description")
    @ColumnInfo(name = "description")
    val description: String = "",

    @SerializedName("language")
    @ColumnInfo(name = "language")
    val language: String = "",

    @SerializedName("languageColor")
    @ColumnInfo(name = "languageColor")
    val languageColor: String = "",

    @SerializedName("stars")
    @ColumnInfo(name = "stars")
    val stars: Int = 0,

    @SerializedName("forks")
    @ColumnInfo(name = "forks")
    val forks: Int = 0,

    @SerializedName("currentPeriodStars")
    @ColumnInfo(name = "currentPeriodStars")
    val currentPeriodStars: Int = 0

   /* @SerializedName("builtBy")
    @ColumnInfo(name = "builtBy")
    val builtBy: List<BuiltBy> = listOf()*/
) {
    data class BuiltBy(
        @SerializedName("username")
        @ColumnInfo(name = "username")
        val username: String = "",
        @SerializedName("href")
        @ColumnInfo(name = "href")
        val href: String = "",
        @SerializedName("avatar")
        @ColumnInfo(name = "avatar")
        val avatar: String = ""
    )
}