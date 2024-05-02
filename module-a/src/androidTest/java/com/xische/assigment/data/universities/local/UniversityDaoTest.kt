package com.xische.assigment.data.universities.local

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.xische.assigment.data.AppDatabase
import com.xische.assigment.data.universities.remote.api.UniversityApi
import com.xische.assigment.domain.common.base.BaseResult
import com.xische.assigment.dummyUniEntityList
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import javax.inject.Inject
import javax.inject.Named

@HiltAndroidTest
class UniversityDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val hiltAndroidRule = HiltAndroidRule(this)

    @Inject
    @Named("test_db")
    lateinit var appDatabase: AppDatabase

    private lateinit var universityDao: UniversityDao

    @Before
    fun setup() {
        hiltAndroidRule.inject()
        universityDao = appDatabase.universityDao()
    }

    @After
    fun teardown() {
        appDatabase.close()
    }

    @Test
    fun insertUser() = runTest {
        val data = dummyUniEntityList()
        universityDao.insertAll(data)
        val allShoppingItems = universityDao.findAll()
        assertTrue(allShoppingItems.containsAll(data))
    }

}