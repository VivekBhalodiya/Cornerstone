/*
 * Created by Vivek Bhalodiya on 20/11/19 11:03 PM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 20/11/19 11:03 PM
 */

package com.vivekbhalodiya.githubtrending.ui.error

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.vivekbhalodiya.githubtrending.R
import com.vivekbhalodiya.githubtrending.databinding.FragmentErrorStateBinding
import com.vivekbhalodiya.githubtrending.ui.base.BaseFragment
import com.vivekbhalodiya.githubtrending.ui.base.BaseViewModel
import com.vivekbhalodiya.githubtrending.ui.base.navigator.ActivityNavigator
import com.vivekbhalodiya.githubtrending.ui.trending.TrendingReposFragment
import kotlinx.android.synthetic.main.fragment_error_state.*

class ErrorStateFragment : BaseFragment<FragmentErrorStateBinding, BaseViewModel>() {
    override fun getViewModelClass() = BaseViewModel::class.java

    override fun layoutId() = R.layout.fragment_error_state

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        setupRetryButton()
    }

    private fun setupRetryButton() {
        button_retry.setOnClickListener {
            ActivityNavigator.replaceFragment(
                R.id.fragment_container_home,
                TrendingReposFragment(),
                activity as AppCompatActivity
            )
        }
    }
}
