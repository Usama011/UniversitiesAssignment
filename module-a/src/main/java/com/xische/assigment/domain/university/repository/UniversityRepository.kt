package com.xische.assigment.domain.university.repository

import com.xische.assigment.domain.common.base.BaseResult
import com.xische.assigment.domain.common.base.Failure
import com.xische.assigment.domain.university.entity.UniversityEntity
import kotlinx.coroutines.flow.Flow

interface UniversityRepository {
    suspend fun fetchUniversity() : Flow<BaseResult<List<UniversityEntity>, Failure>>
}