/*
 * Created by Vivek Bhalodiya on 21/11/19 7:49 PM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 20/11/19 5:14 PM
 */

package com.vivekbhalodiya.githubtrending.di.module

import android.content.Context
import android.content.IntentFilter
import android.net.ConnectivityManager
import com.vivekbhalodiya.githubtrending.BuildConfig
import com.vivekbhalodiya.githubtrending.data.source.remote.ApiInterface
import com.vivekbhalodiya.githubtrending.injection.qualifiers.ApplicationContext
import com.vivekbhalodiya.githubtrending.utils.NetworkUtils
import com.vivekbhalodiya.githubtrending.utils.OnSubscribeBroadcastReceiver
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
class FakeNetworkModule {
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
    internal fun provideOnSubscribeBroadbcastReceiver(@ApplicationContext context: Context): OnSubscribeBroadcastReceiver {
        val intentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        return OnSubscribeBroadcastReceiver(context, intentFilter)
    }

    @Provides
    @Singleton
    internal fun provideNetworkUtils(@ApplicationContext context: Context,
        onSubscribeBroadcastReceiver: OnSubscribeBroadcastReceiver
    ): NetworkUtils {
        return NetworkUtils(context, onSubscribeBroadcastReceiver)
    }

    @Provides
    @Singleton
    fun providesApiInterface(retrofit: Retrofit): ApiInterface = retrofit.create(
        ApiInterface::class.java)
}