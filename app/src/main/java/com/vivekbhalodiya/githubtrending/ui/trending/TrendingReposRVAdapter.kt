/*
 * Created by Vivek Bhalodiya on 20/11/19 12:57 PM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 20/11/19 12:57 PM
 */

package com.vivekbhalodiya.githubtrending.ui.trending

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.vivekbhalodiya.githubtrending.R
import com.vivekbhalodiya.githubtrending.data.model.GithubTrendingResponse

/**
 * Created by Vivek Patel on 2019-11-20.
 */
class TrendingReposRVAdapter : RecyclerView.Adapter<TrendingReposRVViewHolder>() {
    private var trendingReposList: List<GithubTrendingResponse> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingReposRVViewHolder {
        return TrendingReposRVViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.layout_item_trending_repo,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = trendingReposList.size

    override fun onBindViewHolder(holder: TrendingReposRVViewHolder, position: Int) {
        val currentItem = trendingReposList[holder.adapterPosition]
        holder.bind(currentItem)

        holder.itemView.setOnClickListener {
            currentItem.expanded = !currentItem.expanded
            notifyItemChanged(holder.adapterPosition)
        }
    }

    fun setData(trendingReposList: List<GithubTrendingResponse>) {
        this.trendingReposList = trendingReposList
        notifyDataSetChanged()
    }
}