package com.xische.assigment.data.universities.remote

import com.xische.assigment.data.common.exception.NoInternetConnectionException
import com.xische.assigment.data.universities.remote.api.UniversityApi
import com.xische.assigment.domain.common.base.BaseResult
import com.xische.assigment.domain.common.base.Failure
import com.xische.assigment.domain.university.entity.UniversityEntity

open class UniversityRemoteSource(private val universityApi: UniversityApi) {
    open suspend fun fetchUniversity(): BaseResult<List<UniversityEntity>, Failure> {
        try {
            val response = universityApi.getUniversityData()
            return if (response.isSuccessful) {
                val universities = mutableListOf<UniversityEntity>()
                response.body()?.forEach { university ->
                    universities.add(
                        UniversityEntity(
                            university.name,
                            university.stateProvince.toString(),
                            university.country,
                            university.alpha_two_code,
                            university.webPages,
                            university.domains
                        )
                    )
                }
                BaseResult.Success(universities)
            } else {
                BaseResult.Error(Failure(response.code(), response.message()))
            }
        } catch (e: NoInternetConnectionException) {
            return BaseResult.Error(Failure(0, e.message))
        } catch (e: Exception) {
            return BaseResult.Error(Failure(-1, e.message.toString()))
        }
    }
}