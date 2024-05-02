package com.xische.assigment.data.universities.module

import android.content.Context
import androidx.room.Room
import com.xische.assigment.data.AppDatabase
import com.xische.assigment.data.universities.remote.api.UniversityApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.mockito.Mockito
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class TestModule {

    @Singleton
    @Provides
    @Named("test_db")
    fun provideTestDB(@ApplicationContext context: Context): AppDatabase {
        return Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
    }
}