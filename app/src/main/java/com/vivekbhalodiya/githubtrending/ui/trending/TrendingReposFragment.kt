/*
 * Created by Vivek Bhalodiya on 19/11/19 11:03 PM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 19/11/19 11:03 PM
 */

package com.vivekbhalodiya.githubtrending.ui.trending


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.SimpleItemAnimator
import com.vivekbhalodiya.githubtrending.R
import com.vivekbhalodiya.githubtrending.databinding.FragmentTrendingReposBinding
import com.vivekbhalodiya.githubtrending.ui.base.BaseFragment

class TrendingReposFragment : BaseFragment<FragmentTrendingReposBinding, TrendingReposViewModel>() {
    private val trendingReposRVAdapter: TrendingReposRVAdapter by lazy { TrendingReposRVAdapter() }

    override fun layoutId() = R.layout.fragment_trending_repos

    override fun getViewModelClass() = TrendingReposViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getGithubTrendingRepos()

        setupRecyclerView()
    }

    private fun getGithubTrendingRepos() {
        viewModel.test()

        viewModel.trendingRepositoriesResult().observe(this, Observer { result ->
            result?.let {
                trendingReposRVAdapter.setData(it)
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
}
