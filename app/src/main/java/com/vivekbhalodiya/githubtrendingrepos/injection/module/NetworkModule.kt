/*
 * Created by Vivek Bhalodiya on 19/11/19 2:03 AM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 19/11/19 2:03 AM
 */

package com.vivekbhalodiya.githubtrendingrepos.injection.module

import android.content.Context
import com.vivekbhalodiya.githubtrendingrepos.BuildConfig
import com.vivekbhalodiya.githubtrendingrepos.data.source.remote.ApiInterface
import com.vivekbhalodiya.githubtrendingrepos.injection.qualifiers.ApplicationContext
import com.vivekbhalodiya.githubtrendingrepos.utils.NetworkUtils
import com.vivekbhalodiya.githubtrendingrepos.utils.OnSubscribeBroadcastReceiver
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by Vivek Patel on 2019-11-19.
 */

@Module
class NetworkModule {
    companion object {
        private const val SOCKET_READ_WRITE_TIMEOUT_IN_SECONDS: Long = 30
        private const val SOCKET_TIMEOUT_IN_MINUTES: Long = 1
    }

    @Provides
    @Singleton
    internal fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(SOCKET_TIMEOUT_IN_MINUTES, TimeUnit.MINUTES)
            .readTimeout(SOCKET_READ_WRITE_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(SOCKET_READ_WRITE_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    internal fun provideNetworkUtils(
        @ApplicationContext context: Context,
        onSubscribeBroadcastReceiver: OnSubscribeBroadcastReceiver
    ): NetworkUtils {
        return NetworkUtils(context, onSubscribeBroadcastReceiver)
    }

    @Provides
    @Singleton
    fun providesApiInterface(retrofit: Retrofit): ApiInterface = retrofit.create(
        ApiInterface::class.java)
}