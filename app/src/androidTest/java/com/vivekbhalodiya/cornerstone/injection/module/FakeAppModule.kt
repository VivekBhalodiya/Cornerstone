/*
 * Created by Vivek Bhalodiya on 21/11/19 7:49 PM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 20/11/19 5:13 PM
 */

package com.vivekbhalodiya.cornerstone.injection.module

import android.content.Context
import android.preference.PreferenceManager
import com.vivekbhalodiya.cornerstone.MyTestApplication
import com.vivekbhalodiya.cornerstone.injection.qualifiers.ApplicationContext
import com.vivekbhalodiya.cornerstone.utils.PrefsUtils
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Vivek Patel on 2019-11-19.
 */

@Module
class FakeAppModule {
    @Provides
    @Singleton
    @ApplicationContext
    fun provideAppContext(application: MyTestApplication): Context {
        return application
    }

  /*@Provides
  @Singleton
  fun provideDatabase(@ApplicationContext context: Context): Database =
      Room.databaseBuilder(
          context,
          Database::class.java,
          AppConstants.DATABASE_NAME
      )
          *//*.addMigrations(MIGRATION_1_2)*//*
            .fallbackToDestructiveMigration()
            .build()*/

  /*@Provides
  @Singleton
  fun provideTrendingRepositoriesDao(
      database: Database
  ): CornerstoneDao = database.getCornerstoneDao()*/

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): PrefsUtils {
        return  PrefsUtils(PreferenceManager.getDefaultSharedPreferences(context))
    }
}