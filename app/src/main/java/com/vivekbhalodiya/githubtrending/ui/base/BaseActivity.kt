/*
 * Created by Vivek Bhalodiya on 18/11/19 11:54 PM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 18/11/19 11:54 PM
 */

package com.vivekbhalodiya.githubtrending.ui.base

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.vivekbhalodiya.githubtrending.ui.base.navigator.Navigator
import com.vivekbhalodiya.githubtrending.utils.NetworkUtils
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

/**
 * Created by Vivek Patel on 2019-11-18.
 */
abstract class BaseActivity<B : ViewDataBinding, VM : ViewModel> : DaggerAppCompatActivity() {
    lateinit var viewModel: VM
    protected lateinit var binding: B
    private var mCompositeDisposable: CompositeDisposable? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var networkUtils: NetworkUtils
    @Inject
    lateinit var navigator: Navigator

    @LayoutRes
    protected abstract fun layoutId(): Int

    abstract fun getViewModelClass(): Class<VM>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Bind the view and bind the viewModel to layout
        bindContentView(layoutId())
        setupStatusBarColorBelowApi23()
    }

    override fun onStart() {
        super.onStart()
        addNetworkConnectivityObserver()
    }

    override fun onStop() {
        clearCompositeDisposable()
        super.onStop()
    }

    protected fun addDisposable(disposable: Disposable?) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = CompositeDisposable()
        }
        disposable?.let {
            mCompositeDisposable!!.add(it)
        }
    }

    protected fun getActivityNavigator() = navigator

    private fun bindContentView(layoutId: Int) {
        binding = DataBindingUtil.setContentView(this, layoutId)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(getViewModelClass())
    }

    private fun addNetworkConnectivityObserver() {
        addDisposable(networkUtils.networkConnectivityStream()
            .subscribe { isConnected ->
                viewModel.let { viewModel ->
                    (viewModel as BaseViewModel).connectionStatus = isConnected
                }
            })
    }

    private fun clearCompositeDisposable() {
        mCompositeDisposable?.let {
            it.dispose()
            it.clear()
            mCompositeDisposable = null
        }
    }

    private fun setupStatusBarColorBelowApi23() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.statusBarColor = Color.WHITE
                window.navigationBarColor = Color.BLACK
            }
        }
    }
}