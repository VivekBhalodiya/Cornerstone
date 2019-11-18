/*
 * Created by Vivek Bhalodiya on 19/11/19 12:19 AM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 19/11/19 12:19 AM
 */

package com.vivekbhalodiya.githubtrendingrepos.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment

/**
 * Created by Vivek Patel on 2019-11-19.
 */
abstract class BaseFragment<B : ViewDataBinding, VM : ViewModel> : DaggerFragment() {

    lateinit var viewModel: VM
    protected lateinit var binding: B
    protected var useActivityScopeForViewModel: Boolean = false

    @LayoutRes
    protected abstract fun layoutId(): Int

    abstract fun getViewModelClass(): Class<VM>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AndroidSupportInjection.inject(this)

        setViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId(), container, false)
        return binding.root
    }

    /**
     * Use Activity scope for view model if you want to share same viewModel instance
     * between activity and fragment
     */
    private fun setViewModel() {
        viewModel = if (useActivityScopeForViewModel) {
            ViewModelProviders.of(
                activity as BaseActivity<*, *>, (activity as BaseActivity<*, *>).viewModelFactory
            )
                .get(getViewModelClass())
        } else {
            ViewModelProviders.of(this, (activity as BaseActivity<*, *>).viewModelFactory)
                .get(getViewModelClass())
        }
    }
}