/*
 * Created by Vivek Bhalodiya on 19/11/19 11:03 PM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 19/11/19 11:03 PM
 */

package com.vivekbhalodiya.githubtrending.ui.trending

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.SimpleItemAnimator
import com.vivekbhalodiya.githubtrending.R
import com.vivekbhalodiya.githubtrending.databinding.FragmentTrendingReposBinding
import com.vivekbhalodiya.githubtrending.ui.base.BaseFragment
import com.vivekbhalodiya.githubtrending.ui.base.navigator.ActivityNavigator
import com.vivekbhalodiya.githubtrending.ui.error.ErrorStateFragment
import com.vivekbhalodiya.githubtrending.ui.home.HomeActivity

class TrendingReposFragment : BaseFragment<FragmentTrendingReposBinding, TrendingReposViewModel>() {
    private val trendingReposRVAdapter: TrendingReposRVAdapter by lazy { TrendingReposRVAdapter() }

    override fun layoutId() = R.layout.fragment_trending_repos

    override fun getViewModelClass() = TrendingReposViewModel::class.java

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        getGithubTrendingRepos()

        setupRecyclerView()

        setupSwipeToRefresh()
    }

    private fun getGithubTrendingRepos() {
        viewModel.getGithubTrendingRepos()

        viewModel.trendingRepositoriesResult()
            .observe(this, Observer { result ->
                result?.let {
                    if (it.isEmpty()) {
                        showErrorState()
                    } else {
                        trendingReposRVAdapter.setData(it)
                        dismissSwipeRefresh()
                    }
                }
            })
    }

    private fun setupRecyclerView() {
        with(binding.recyclerviewTrendingRepos) {
            (itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            binding.recyclerviewTrendingRepos.adapter = trendingReposRVAdapter
            setHasFixedSize(true)
        }
    }

    private fun setupSwipeToRefresh() {
        binding.layoutSwiperefresh.setOnRefreshListener {
            viewModel.getGithubTrendingRepos()
        }
    }

    private fun dismissSwipeRefresh() {
        with(binding.layoutSwiperefresh) {
            if (isRefreshing) isRefreshing = false
        }
    }

    private fun showErrorState() {
        ActivityNavigator.replaceFragment(
            R.id.fragment_container_home,
            ErrorStateFragment(),
            activity as AppCompatActivity
        )
    }
}
