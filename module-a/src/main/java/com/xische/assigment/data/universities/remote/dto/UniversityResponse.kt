package com.xische.assigment.data.universities.remote.dto

import com.google.gson.annotations.SerializedName

data class UniversityResponse(
    @SerializedName("state-province") var stateProvince: String?,
    @SerializedName("name") var name: String,
    @SerializedName("alpha_two_code") var alpha_two_code: String,
    @SerializedName("country") var country: String,
    @SerializedName("web_pages") var webPages: List<String> = ArrayList(),
    @SerializedName("domains") var domains: List<String> =  ArrayList()
)