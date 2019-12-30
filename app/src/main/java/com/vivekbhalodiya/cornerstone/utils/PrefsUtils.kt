/*
 * Created by Vivek Bhalodiya on 19/11/19 10:14 PM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 19/11/19 10:14 PM
 */

package com.vivekbhalodiya.cornerstone.utils

import android.content.SharedPreferences
import javax.inject.Inject

/**
 * Created by Vivek Patel on 2019-11-19.
 */
class PrefsUtils @Inject constructor(private val prefs: SharedPreferences) {
  companion object {
    private const val FRESH_DATA_TIMESTAMP = "INSERTED_DATA_TIMESTAMP"
  }

  var freshDataTimeStamp: Long
    get() = prefs.getLong(FRESH_DATA_TIMESTAMP, 0L)
    set(value) = prefs.edit().putLong(FRESH_DATA_TIMESTAMP, value).apply()
}