/*
 * Created by Vivek Bhalodiya on 19/11/19 1:25 AM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 18/11/19 7:01 PM
 */

package com.vivekbhalodiya.githubtrending.ui.home

import android.os.Bundle
import com.vivekbhalodiya.githubtrending.R
import com.vivekbhalodiya.githubtrending.databinding.ActivityHomeBinding
import com.vivekbhalodiya.githubtrending.ui.base.BaseActivity
import com.vivekbhalodiya.githubtrending.ui.base.navigator.ActivityNavigator
import com.vivekbhalodiya.githubtrending.ui.trending.TrendingReposFragment

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

        //Navigate to TrendingReposFragment
        ActivityNavigator.replaceFragment(
            R.id.fragment_container_home,
            TrendingReposFragment(),
            this
        )
    }

    /**
     *  This functions sets up a Custom toolbar.
     */
    private fun setupToolbar() {
        setSupportActionBar(binding.layoutToolbarWhite.toolbarWhite)
        supportActionBar?.title = ""
    }
}