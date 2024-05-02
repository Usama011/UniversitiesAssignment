package com.xische.assigment.domain.university.repository

import com.xische.assigment.domain.common.base.BaseResult
import com.xische.assigment.domain.common.base.Failure
import com.xische.assigment.domain.university.entity.UniversityEntity
import com.xische.assigment.getDummyData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeUniversityRepository : UniversityRepository {

    override suspend fun fetchUniversity(): Flow<BaseResult<List<UniversityEntity>, Failure>> {
        return flow { emit(getDummyData()) }
    }

}
