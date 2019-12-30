/*
 * Created by Vivek Bhalodiya on 19/11/19 1:25 AM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 18/11/19 7:01 PM
 */

package com.vivekbhalodiya.cornerstone.ui.home

import android.os.Bundle
import com.vivekbhalodiya.cornerstone.R
import com.vivekbhalodiya.cornerstone.databinding.ActivityHomeBinding
import com.vivekbhalodiya.cornerstone.ui.base.BaseActivity

/**
 * This is the main launcher activity
 *
 * This activity starts TrendingReposFragments as soon as Toolbar is setup.
 */

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>() {
  override fun getViewModelClass() = HomeViewModel::class.java

  override fun layoutId() = R.layout.activity_home

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setupToolbar()
  }

  /**
   *  This functions sets up a Custom toolbar.
   */
  private fun setupToolbar() {
    setSupportActionBar(binding.layoutToolbarWhite.toolbarWhite)
    supportActionBar?.title = ""
  }
}