/*
 * Created by Vivek Bhalodiya on 19/11/19 1:05 AM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 19/11/19 1:05 AM
 */

package com.vivekbhalodiya.cornerstone.injection.module

import android.content.Context
import android.preference.PreferenceManager
import com.vivekbhalodiya.cornerstone.CornerstoneApp
import com.vivekbhalodiya.cornerstone.injection.qualifiers.ApplicationContext
import com.vivekbhalodiya.cornerstone.utils.PrefsUtils
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Vivek Patel on 2019-11-19.
 */

@Module
class AppModule {
  /*companion object {
    val MIGRATION_1_2: Migration = object : Migration(1, 2) {
      override fun migrate(database: SupportSQLiteDatabase) {
        //Change the table name
        database.execSQL("ALTER TABLE trendingrepos RENAME TO trendingrepos2")
      }
    }
  }*/

  @Provides
  @Singleton
  @ApplicationContext
  fun provideAppContext(application: CornerstoneApp): Context {
    return application
  }

  /*@Provides
  @Singleton
  fun provideTrendingRepositoriesDatabase(@ApplicationContext context: Context): Database =
    Room.databaseBuilder(
        context,
        Database::class.java,
        AppConstants.DATABASE_NAME
    )
        *//*.addMigrations(MIGRATION_1_2)*//*
        .fallbackToDestructiveMigration()
        .build()

  @Provides
  @Singleton
  fun provideTrendingRepositoriesDao(
    database: Database
  ): CornerstoneDao = database.getCornerstoneDao()*/

  @Provides
  @Singleton
  fun provideSharedPreferences(@ApplicationContext context: Context): PrefsUtils {
    return PrefsUtils(PreferenceManager.getDefaultSharedPreferences(context))
  }
}