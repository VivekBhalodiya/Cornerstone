/*
 * Created by Vivek Bhalodiya on 19/11/19 4:30 PM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 19/11/19 4:23 PM
 */

package com.vivekbhalodiya.cornerstone.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created by Vivek Patel on 2019-11-19.
 */

@Entity(
    tableName = "cornerstonetable"
)
data class CornerstoneResponse(
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "id")
  val id: Long? = null,

  @SerializedName("author")
  @ColumnInfo(name = "author")
  val author: String? = null,

  @SerializedName("name")
  @ColumnInfo(name = "name")
  val name: String? = null,

  @SerializedName("avatar")
  @ColumnInfo(name = "avatar")
  val avatar: String? = null,

  @SerializedName("url")
  @ColumnInfo(name = "url")
  val url: String? = null,

  @SerializedName("description")
  @ColumnInfo(name = "description")
  val description: String? = null,

  @SerializedName("language")
  @ColumnInfo(name = "language")
  val language: String? = null,

  @SerializedName("languageColor")
  @ColumnInfo(name = "languageColor")
  val languageColor: String? = null,

  @SerializedName("stars")
  @ColumnInfo(name = "stars")
  val stars: Int? = null,

  @SerializedName("forks")
  @ColumnInfo(name = "forks")
  val forks: Int? = null,

  @SerializedName("currentPeriodStars")
  @ColumnInfo(name = "currentPeriodStars")
  val currentPeriodStars: Int? = null,

    /*@TypeConverters(CornerstoneConverters::class)
    val builtBy: ArrayList<BuiltBy>? = null,
*/
  var expanded: Boolean = false

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