package com.xische.assigment

import com.xische.assigment.data.universities.remote.dto.UniversityResponse
import com.xische.assigment.domain.common.base.BaseResult
import com.xische.assigment.domain.common.base.Failure
import com.xische.assigment.domain.university.entity.UniversityEntity

val webPageList = listOf("https://mbzuai.ac.ae/")
val domainList = listOf("mbzuai.ac.ae")
fun dummyList(): List<UniversityResponse> {
    val data1 = UniversityResponse(
        "Abu Dhabi",
        "Mohamed bin Zayed University of Artificial Intelligence (MBZUAI)",
        "AE",
        "United Arab Emirates",
        webPageList,
        domainList
    )
    return listOf(data1)
}

fun dummyUniEntityList(): List<UniversityEntity> {
    val universities = mutableListOf<UniversityEntity>()
    dummyList().forEach { university ->
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
    return universities
}

fun getDummyData(): BaseResult<List<UniversityEntity>, Failure> {
    val universities = mutableListOf<UniversityEntity>()
    dummyList().forEach { university ->
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
    return BaseResult.Success(universities)
}