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
    @Provides
    @Singleton
    internal fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
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