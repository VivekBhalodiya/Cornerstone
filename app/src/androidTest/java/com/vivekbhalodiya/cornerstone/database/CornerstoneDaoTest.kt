/*
 * Created by Vivek Bhalodiya on 21/11/19 6:48 PM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 21/11/19 6:48 PM
 */

package com.vivekbhalodiya.cornerstone.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.vivekbhalodiya.cornerstone.data.FakeReposSource
import com.vivekbhalodiya.cornerstone.data.source.local.CornerstoneDao
import com.vivekbhalodiya.cornerstone.data.source.local.Database
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Vivek Patel on 2019-11-21.
 */
@RunWith(AndroidJUnit4::class)
class CornerstoneDaoTest {

    @get:Rule val instantTaskExecutorRule = InstantTaskExecutorRule()

    private var mDatabase: Database? = null

    private var cornerstoneDao: CornerstoneDao? = null

    @Before
    @Throws(Exception::class)
    fun initDb() {
        mDatabase = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().context,
            Database::class.java
        )
            .allowMainThreadQueries()
            .build()

        cornerstoneDao = mDatabase!!.getCornerstoneDao()
    }

    @After
    @Throws(Exception::class)
    fun closeDb() {
        mDatabase!!.close()
    }

    @Test
    @Throws(InterruptedException::class)
    fun onFetchingTrendingRepos_shouldGetEmptyList_IfTable_IsEmpty() {
        val reposList = cornerstoneDao!!.getGithubTrendingRepos()
            .blockingFirst()
        assertTrue(reposList.isEmpty())
    }

    @Test
    @Throws(InterruptedException::class)
    fun onInsertingTrendingRepos_checkIf_RowCountIsCorrect() {
        val fakeList = FakeReposSource.getFakeData(5)
        fakeList.forEach { repo -> cornerstoneDao!!.insert() }
        assertEquals(5, cornerstoneDao!!.getGithubTrendingRepos().blockingFirst().size)
    }

    @Test
    @Throws(InterruptedException::class)
    fun onTrendingRepoDeletion_CheckIf_TrendingRepoIsDeletedFromTable() {
        val trendingReposList = FakeReposSource.getFakeData(5)
        trendingReposList.forEach { note -> cornerstoneDao!!.insert(note) }
        cornerstoneDao!!.deleteAll()
        assertTrue(cornerstoneDao!!.getGithubTrendingRepos().blockingFirst().isEmpty())
    }
}