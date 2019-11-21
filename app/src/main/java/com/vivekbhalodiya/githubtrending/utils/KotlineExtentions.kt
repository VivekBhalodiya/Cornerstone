/*
 * Created by Vivek Bhalodiya on 19/11/19 12:25 PM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 19/11/19 12:25 PM
 */

package com.vivekbhalodiya.githubtrending.utils

import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Vivek Patel on 2019-11-19.
 */

fun <T> Observable<T>.onBackground(): Observable<T> {
    return this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

fun <T> Single<T>.onBackground(): Single<T> {
    return this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

fun <T> Maybe<T>.onBackground(): Maybe<T> {
    return this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

fun <T> Flowable<T>.onBackground(): Flowable<T> {
    return this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

/**
 * @return Not Available placeholder for empty or null string
 */
fun String?.toNonEmptyString(): String {
    return if (this.isNullOrEmpty()) "-" else this
}