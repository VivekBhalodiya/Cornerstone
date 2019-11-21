/*
 * Created by Vivek Bhalodiya on 19/11/19 11:03 PM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 19/11/19 11:03 PM
 */

package com.vivekbhalodiya.githubtrending.ui.trending

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.View.GONE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.SimpleItemAnimator
import com.vivekbhalodiya.githubtrending.R
import com.vivekbhalodiya.githubtrending.databinding.FragmentTrendingReposBinding
import com.vivekbhalodiya.githubtrending.ui.base.BaseFragment
import com.vivekbhalodiya.githubtrending.ui.base.navigator.ActivityNavigator
import com.vivekbhalodiya.githubtrending.ui.error.ErrorStateFragment
import com.vivekbhalodiya.githubtrending.utils.OrderType
import com.vivekbhalodiya.githubtrending.utils.TrendingReposOrderBy

/**
 * This Fragment displays the list of Github's Trending repositories which is being
 * supplied by the TrendingReposViewModel
 */
class TrendingReposFragment : BaseFragment<FragmentTrendingReposBinding, TrendingReposViewModel>() {
    private val trendingReposRVAdapter: TrendingReposRVAdapter by lazy { TrendingReposRVAdapter() }

    override fun layoutId() = R.layout.fragment_trending_repos

    override fun getViewModelClass() = TrendingReposViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        getGithubTrendingRepos()

        setupSwipeToRefresh()

        observeErrors()
    }

    /**
     * If any errors occurs while fetching the data from the network or database,
     * this function will replace this fragment with ErrorStateFragment
     */

    private fun observeErrors() {
        viewModel.trendingRepositoriesError().observe(viewLifecycleOwner, Observer {
            it?.let {
                stopShimmer()
                showErrorState()
            }
        })
    }

    /**
     * This function talks to the TrendingReposViewModel and fetches the TrendingRepos list.
     */
    private fun getGithubTrendingRepos() {
        getGithubTrendingReposOrderBy()

        viewModel.trendingRepositoriesResult()
            .observe(viewLifecycleOwner, Observer { result ->
                result?.let {
                    trendingReposRVAdapter.setData(it)
                    stopShimmer()
                    dismissSwipeRefresh()
                }
            })
    }

    /**
     * This functions initialize the recyclerView and adds a Divider to every item.
     */

    private fun setupRecyclerView() {
        with(binding.recyclerviewTrendingRepos) {
            (itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            binding.recyclerviewTrendingRepos.adapter = trendingReposRVAdapter
            setHasFixedSize(true)
        }
    }

    /**
     * This function talks to TrendingReposViewModel to query the list based on the OrderBy
     */
    private fun getGithubTrendingReposOrderBy(orderBy: TrendingReposOrderBy = TrendingReposOrderBy.DEFAULT) {
        when (orderBy) {
            TrendingReposOrderBy.NAME -> {
                viewModel.getGithubTrendingRepos(
                    orderBy = TrendingReposOrderBy.NAME,
                    orderType = OrderType.DESC.name
                )
            }
            TrendingReposOrderBy.STARS -> {
                viewModel.getGithubTrendingRepos(
                    orderBy = TrendingReposOrderBy.STARS,
                    orderType = OrderType.DESC.name
                )
            }
            else -> viewModel.getGithubTrendingRepos()
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

    private fun stopShimmer() {
        binding.layoutShimmer.stopShimmer()
        binding.layoutShimmer.visibility = GONE
    }

    private fun startShimmer() {
        binding.layoutShimmer.startShimmer()
    }

    override fun onCreateOptionsMenu(
        menu: Menu,
        inflater: MenuInflater
    ) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_item_sort_name -> {
                getGithubTrendingReposOrderBy(TrendingReposOrderBy.NAME)
                true
            }
            R.id.menu_item_sort_stars -> {
                getGithubTrendingReposOrderBy(TrendingReposOrderBy.STARS)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onResume() {
        startShimmer()
        super.onResume()
    }

    override fun onPause() {
        stopShimmer()
        super.onPause()
    }
}
