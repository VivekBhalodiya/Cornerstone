/*
 * Created by Vivek Bhalodiya on 19/11/19 12:11 AM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 19/11/19 12:11 AM
 */

package com.vivekbhalodiya.githubtrendingrepos.ui.base

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import com.vivekbhalodiya.githubtrendingrepos.utils.NetworkUtils
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Vivek Patel on 2019-11-19.
 */
abstract class BaseViewModel : ViewModel(), LifecycleObserver {
    private var mCompositeDisposable: CompositeDisposable? = null
    internal var connectionStatus: Boolean? = null

    @Inject
    lateinit var networkUtils: NetworkUtils

    override fun onCleared() {
        super.onCleared()
        Timber.d("unsubscribeFromDataStore(): ")
        clearCompositeDisposable()
    }

    protected fun isConnected() = connectionStatus

    protected fun addDisposable(disposable: Disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = CompositeDisposable()
        }
        mCompositeDisposable!!.add(disposable)
    }

    private fun clearCompositeDisposable() {
        mCompositeDisposable?.let {
            it.dispose()
            it.clear()
            mCompositeDisposable = null
        }
    }
}