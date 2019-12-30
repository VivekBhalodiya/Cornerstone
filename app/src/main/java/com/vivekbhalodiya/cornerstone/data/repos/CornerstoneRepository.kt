/*
 * Created by Vivek Bhalodiya on 19/11/19 5:30 PM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 19/11/19 5:30 PM
 */

package com.vivekbhalodiya.cornerstone.data.repos

import com.vivekbhalodiya.cornerstone.data.source.remote.ApiInterface
import com.vivekbhalodiya.cornerstone.utils.NetworkUtils
import com.vivekbhalodiya.cornerstone.utils.PrefsUtils
import javax.inject.Inject

/**
 * Created by Vivek Patel on 2019-11-19.
 */
class CornerstoneRepository @Inject constructor(
  private val apiInterface: ApiInterface,
  private val networkUtils: NetworkUtils,
  private val prefsUtils: PrefsUtils
) {
  fun getData() {
  }

  private fun getFromDb() {
  }

  private fun getFromApi() {
  }
}