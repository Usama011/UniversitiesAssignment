package com.xische.assigment.domain.university.usecase

import com.xische.assigment.domain.common.base.BaseResult
import com.xische.assigment.domain.common.base.Failure
import com.xische.assigment.domain.university.entity.UniversityEntity
import com.xische.assigment.domain.university.repository.UniversityRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchUniversityUseCase @Inject constructor(private val universityRepository: UniversityRepository) {
    suspend fun invoke() : Flow<BaseResult<List<UniversityEntity>, Failure>> {
        return universityRepository.fetchUniversity()
    }
}