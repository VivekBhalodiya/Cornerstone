/*
 * Created by Vivek Bhalodiya on 18/11/19 11:54 PM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 18/11/19 11:54 PM
 */

package com.vivekbhalodiya.githubtrendingrepos.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

/**
 * Created by Vivek Patel on 2019-11-18.
 */
abstract class BaseActivity<B : ViewDataBinding, VM : ViewModel> : DaggerAppCompatActivity() {
    lateinit var viewModel: VM
    protected lateinit var binding: B

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @LayoutRes
    protected abstract fun layoutId(): Int

    abstract fun getViewModelClass(): Class<VM>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Bind the view and bind the viewModel to layout
        bindContentView(layoutId())
    }

    private fun bindContentView(layoutId: Int) {
        binding = DataBindingUtil.setContentView(this, layoutId)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(getViewModelClass())
    }
}