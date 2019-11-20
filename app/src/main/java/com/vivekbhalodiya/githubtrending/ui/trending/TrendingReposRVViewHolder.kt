/*
 * Created by Vivek Bhalodiya on 20/11/19 12:58 PM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 20/11/19 12:58 PM
 */

package com.vivekbhalodiya.githubtrending.ui.trending

import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.vivekbhalodiya.githubtrending.data.model.GithubTrendingResponse
import com.vivekbhalodiya.githubtrending.databinding.LayoutItemTrendingRepoBinding

/**
 * Created by Vivek Patel on 2019-11-20.
 */
class TrendingReposRVViewHolder(private val viewBinding: LayoutItemTrendingRepoBinding) :
    RecyclerView.ViewHolder(viewBinding.root) {

    fun bind(trendingRepo: GithubTrendingResponse) {
        with(viewBinding) {
            textviewAuthor.text = trendingRepo.author
            textviewRepoName.text = trendingRepo.name
            setNetworkImage(imageviewAvatar, trendingRepo.avatar, true)
            if (trendingRepo.expanded) {
                textviewDescription.text = trendingRepo.description
                textviewLanguage.text = trendingRepo.language
                textviewStarsCount.text = trendingRepo.stars.toString()
                textviewForkCount.text = trendingRepo.forks.toString()
                expandedLayout.visibility = VISIBLE
            } else {
                expandedLayout.visibility = GONE
            }
        }
    }

    private fun setNetworkImage(targetImageView: ImageView, url: String?, circleCrop: Boolean) {
        val glide = Glide.with(viewBinding.root)
            .load(url)

        if (circleCrop) {
            glide.apply {
                apply(RequestOptions.circleCropTransform())
                into(targetImageView)
            }
        } else {
            glide.into(targetImageView)
        }
    }
}