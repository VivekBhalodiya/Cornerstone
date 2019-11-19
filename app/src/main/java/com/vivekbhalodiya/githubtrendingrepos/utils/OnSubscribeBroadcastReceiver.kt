/*
 * Created by Vivek Bhalodiya on 19/11/19 12:22 PM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 19/11/19 12:22 PM
 */

package com.vivekbhalodiya.githubtrendingrepos.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import com.vivekbhalodiya.githubtrendingrepos.injection.qualifiers.ApplicationContext
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.disposables.Disposables
import timber.log.Timber

/**
 * Created by Vivek Patel on 2019-11-19.
 */
class OnSubscribeBroadcastReceiver constructor(
    @ApplicationContext private var context: Context,
    private val intentFilter: IntentFilter
) :
    ObservableOnSubscribe<Intent> {
    override fun subscribe(emitter: ObservableEmitter<Intent>) {

        val broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(
                context: Context?,
                intent: Intent?
            ) {
                intent?.let { emitter.onNext(it) }
            }
        }

        val disposable = Disposables.fromAction {
            try {
                broadcastReceiver.let {
                    context.unregisterReceiver(broadcastReceiver)
                }
            } catch (e: Exception) {
                Timber.e(e)
            }
        }

        emitter.setDisposable(disposable)
        context.registerReceiver(broadcastReceiver, intentFilter)
    }
}