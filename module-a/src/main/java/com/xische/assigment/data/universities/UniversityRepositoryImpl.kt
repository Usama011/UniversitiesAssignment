package com.xische.assigment.data.universities

import com.xische.assigment.data.universities.local.UniversityDao
import com.xische.assigment.data.universities.remote.UniversityRemoteSource
import com.xische.assigment.domain.common.base.BaseResult
import com.xische.assigment.domain.common.base.Failure
import com.xische.assigment.domain.university.entity.UniversityEntity
import com.xische.assigment.domain.university.repository.UniversityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UniversityRepositoryImpl(
    private val universityRemoteSource: UniversityRemoteSource,
    private val universityDao: UniversityDao
) : UniversityRepository {

    override suspend fun fetchUniversity(): Flow<BaseResult<List<UniversityEntity>, Failure>> {
        return flow {
            val result = universityRemoteSource.fetchUniversity()
            if (result is BaseResult.Success) {
                saveToLocal(result.data)
                emit(result)
            } else {
                val localUniversity = universityDao.findAll()
                if (localUniversity.isNotEmpty())
                    emit(BaseResult.Success(localUniversity))
                else {
                    emit(result)
                }
            }
        }
    }


    private fun saveToLocal(universityEntity: List<UniversityEntity>) {
        universityDao.deleteAll()
        universityDao.insertAll(universityEntity)
    }
}