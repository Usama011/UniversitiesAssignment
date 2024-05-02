package com.xische.assigment.data.universities.module

import com.xische.assigment.data.AppDatabase
import com.xische.assigment.data.universities.UniversityRepositoryImpl
import com.xische.assigment.data.universities.local.UniversityDao
import com.xische.assigment.data.universities.remote.UniversityRemoteSource
import com.xische.assigment.data.universities.remote.api.UniversityApi
import com.xische.assigment.domain.university.repository.UniversityRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UniversityModule {

    @Provides
    @Singleton
    fun provideUniversityRemoteApi(retrofit: Retrofit): UniversityApi {
        return retrofit.create(UniversityApi::class.java)
    }

    @Provides
    @Singleton
    fun provideUniversityRemoteSource(universityApi: UniversityApi): UniversityRemoteSource {
        return UniversityRemoteSource(universityApi)
    }

    @Provides
    @Singleton
    fun provideUniversityDao(appDatabase: AppDatabase) : UniversityDao {
        return appDatabase.universityDao()
    }

    @Provides
    @Singleton
    fun provideUniversityRepository(
        universityRemoteSource: UniversityRemoteSource,
        universityDao: UniversityDao
    ): UniversityRepository {
        return UniversityRepositoryImpl(universityRemoteSource, universityDao)
    }
}