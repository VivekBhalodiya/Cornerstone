/*
 * Created by Vivek Bhalodiya on 22/11/19 12:23 AM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 22/11/19 12:22 AM
 */

package com.vivekbhalodiya.githubtrending.data.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vivekbhalodiya.githubtrending.data.model.GithubTrendingResponse

/**
 * Created by Vivek Patel on 2019-11-22.
 */
class BuiltByConverters {
    companion object {
        private val mGson = Gson()

        @TypeConverter
        fun fromBuiltBy(builtBy: ArrayList<GithubTrendingResponse.BuiltBy>?): String {
            return mGson.toJson(builtBy)
        }

        @TypeConverter
        fun toBuiltBy(builtBy: String): ArrayList<GithubTrendingResponse.BuiltBy>? {
            val type = object : TypeToken<ArrayList<GithubTrendingResponse.BuiltBy>>() {}.type
            return mGson.fromJson(builtBy, type)
        }
    }
}