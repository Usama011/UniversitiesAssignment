package com.xische.assigment.data.universities.remote.api

import com.xische.assigment.data.universities.remote.dto.UniversityResponse
import retrofit2.Response
import retrofit2.http.GET

interface UniversityApi {
    @GET("search?country=United Arab Emirates")
    suspend fun getUniversityData() : Response<List<UniversityResponse>>
}