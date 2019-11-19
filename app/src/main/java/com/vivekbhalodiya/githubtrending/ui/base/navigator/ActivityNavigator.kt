/*
 * Created by Vivek Bhalodiya on 19/11/19 11:36 PM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 11/10/19 1:34 PM
 */

package com.vivekbhalodiya.githubtrending.ui.base.navigator

import androidx.appcompat.app.AppCompatActivity

class ActivityNavigator constructor(private val activity: AppCompatActivity) : Navigator() {
    override fun getActivity(): AppCompatActivity = activity
}