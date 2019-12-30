/*
 * Created by Vivek Bhalodiya on 19/11/19 11:18 PM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 19/11/19 1:09 PM
 */

package com.vivekbhalodiya.cornerstone.utils.livedata

import androidx.annotation.MainThread
import androidx.annotation.Nullable
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import timber.log.Timber
import java.util.concurrent.atomic.AtomicBoolean

class SingleLiveEvent<T> : MutableLiveData<T>() {
  private val mPending = AtomicBoolean(false)

  @MainThread
  override fun observe(
    owner: LifecycleOwner,
    observer: Observer<in T>
  ) {
    if (hasActiveObservers()) {
      Timber.w("Multiple observers registered but only one will be notified of changes.")
    }

    // Observe the internal MutableLiveData
    super.observe(owner, Observer<T> { t ->
      if (mPending.compareAndSet(true, false)) {
        observer.onChanged(t)
      }
    })
  }

  @MainThread
  override fun setValue(@Nullable t: T?) {
    mPending.set(true)
    super.setValue(t)
  }

  @MainThread
  override fun postValue(value: T) {
    mPending.set(true)
    super.postValue(value)
  }

  /**
   * Used for cases where T is Void, to make calls cleaner.
   */
  @MainThread
  fun call() {
    this.value = null
  }
}

open class Event<out T>(private val content: T) {

  var hasBeenHandled = false
    private set // Allow external read but not write

  /**
   * Returns the content and prevents its use again.
   */
  fun getContentIfNotHandled(): T? {
    return if (hasBeenHandled) {
      null
    } else {
      hasBeenHandled = true
      content
    }
  }

  /**
   * Returns the content, even if it's already been handled.
   */
  fun peekContent(): T = content
}