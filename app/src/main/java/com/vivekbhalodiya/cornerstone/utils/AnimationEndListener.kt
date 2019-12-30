/*
 * Created by Vivek Bhalodiya on 22/11/19 3:13 AM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 22/11/19 3:13 AM
 */

package com.vivekbhalodiya.cornerstone.utils

import android.view.animation.Animation
import timber.log.Timber

/**
 * Created by Vivek Patel on 2019-11-22.
 */
abstract class AnimationEndListener : Animation.AnimationListener {
    abstract fun onAnimationEnded()

    override fun onAnimationRepeat(p0: Animation?) {
        Timber.d("Not implemented")
    }

    override fun onAnimationEnd(p0: Animation?) {
        onAnimationEnded()
    }

    override fun onAnimationStart(p0: Animation?) {
        Timber.d("Not implemented")
    }
}