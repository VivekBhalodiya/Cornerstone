/*
 * Created by Vivek Bhalodiya on 21/11/19 6:48 PM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 21/11/19 6:48 PM
 */

package com.vivekbhalodiya.githubtrending.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.vivekbhalodiya.githubtrending.data.FakeTrendingReposSource
import com.vivekbhalodiya.githubtrending.data.source.local.Database
import com.vivekbhalodiya.githubtrending.data.source.local.GithubTrendingDao
import junit.framework.TestCase.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Vivek Patel on 2019-11-21.
 */
@RunWith(AndroidJUnit4::class)
class GithubTrendingDaoTest {

    @get:Rule val instantTaskExecutorRule = InstantTaskExecutorRule()

    private var mDatabase: Database? = null

    private var gihubTrendingDao: GithubTrendingDao? = null

    @Before
    @Throws(Exception::class)
    fun initDb() {
        mDatabase = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().context,
            Database::class.java
        )
            .allowMainThreadQueries()
            .build()

        gihubTrendingDao = mDatabase!!.trendingRepositoriesDao()
    }

    @After
    @Throws(Exception::class)
    fun closeDb() {
        mDatabase!!.close()
    }

    @Test
    @Throws(InterruptedException::class)
    fun onFetchingTrendingRepos_shouldGetEmptyList_IfTable_IsEmpty() {
        val reposList = gihubTrendingDao!!.getGithubTrendingRepos().blockingFirst()
        assertTrue(reposList.isEmpty())
    }

    @Test
    @Throws(InterruptedException::class)
    fun onInsertingTrendingRepos_checkIf_RowCountIsCorrect() {
        val trendingReposList = FakeTrendingReposSource.getFakeTrendingRepos(5)
        trendingReposList.forEach { repo -> gihubTrendingDao!!.insertGithubTrendingRepos(repo) }
        assertEquals(5, gihubTrendingDao!!.getGithubTrendingRepos().blockingFirst().size)
    }

    @Test
    @Throws(InterruptedException::class)
    fun onTrendingRepoDeletion_CheckIf_TrendingRepoIsDeletedFromTable() {
        val trendingReposList = FakeTrendingReposSource.getFakeTrendingRepos(5)
        trendingReposList.forEach { note -> gihubTrendingDao!!.insertGithubTrendingRepos(note) }
        gihubTrendingDao!!.deleteAll()
        assertTrue(gihubTrendingDao!!.getGithubTrendingRepos().blockingFirst().isEmpty())
    }
}