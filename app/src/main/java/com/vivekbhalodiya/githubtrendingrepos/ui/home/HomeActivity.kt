/*
 * Created by Vivek Bhalodiya on 19/11/19 1:25 AM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 18/11/19 7:01 PM
 */

package com.vivekbhalodiya.githubtrendingrepos.ui.home

import android.os.Bundle
import com.vivekbhalodiya.githubtrendingrepos.R
import com.vivekbhalodiya.githubtrendingrepos.databinding.ActivityHomeBinding
import com.vivekbhalodiya.githubtrendingrepos.ui.base.BaseActivity

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>() {
    override fun getViewModelClass() = HomeViewModel::class.java

    override fun layoutId() = R.layout.activity_home

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}