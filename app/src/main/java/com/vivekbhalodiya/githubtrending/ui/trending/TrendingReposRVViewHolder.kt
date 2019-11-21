/*
 * Created by Vivek Bhalodiya on 20/11/19 12:58 PM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 20/11/19 12:58 PM
 */

package com.vivekbhalodiya.githubtrending.ui.trending

import android.graphics.Color
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.vivekbhalodiya.githubtrending.data.model.GithubTrendingResponse
import com.vivekbhalodiya.githubtrending.databinding.LayoutItemTrendingRepoBinding
import com.vivekbhalodiya.githubtrending.utils.makeFadeVisible
import com.vivekbhalodiya.githubtrending.utils.toNonEmptyString


/**
 * Created by Vivek Patel on 2019-11-20.
 */
class TrendingReposRVViewHolder(private val viewBinding: LayoutItemTrendingRepoBinding) :
    RecyclerView.ViewHolder(viewBinding.root) {

    fun bind(trendingRepo: GithubTrendingResponse) {
        with(viewBinding) {
            textviewAuthor.text = trendingRepo.author.toNonEmptyString()
            textviewRepoName.text = trendingRepo.name.toNonEmptyString()
            setNetworkImage(imageviewAvatar, trendingRepo.avatar, true)

            //Expand the detail layout
            if (trendingRepo.expanded) {
                expandDetailLayout(trendingRepo)
            } else {
                expandedLayout.visibility = GONE
            }
        }
    }

    private fun LayoutItemTrendingRepoBinding.expandDetailLayout(trendingRepo: GithubTrendingResponse) {
        expandedLayout.visibility = VISIBLE
        setLanguageColor(imageviewLanguageColor, trendingRepo.languageColor)
        textviewDescription.text = trendingRepo.description.toNonEmptyString()
        textviewLanguage.text = trendingRepo.language.toNonEmptyString()
        textviewStarsCount.text = trendingRepo.stars.toString().toNonEmptyString()
        textviewForkCount.text = trendingRepo.forks.toString().toNonEmptyString()
        //Make fade animation
        textviewDescription.makeFadeVisible()
        tablerowStats.makeFadeVisible()
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

    private fun setLanguageColor(
        imageviewLanguageColor: ImageView,
        languageColor: String?
    ) {
        languageColor?.let {
            imageviewLanguageColor.setColorFilter(Color.parseColor(it))
        }
    }
}
