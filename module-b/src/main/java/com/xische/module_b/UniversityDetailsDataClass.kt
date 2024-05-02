package com.xische.module_b

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import com.google.gson.annotations.SerializedName

@Parcelize
data class UniversityDetailsDataClass(
    @SerializedName("name") var name: String,
    @SerializedName("state-province") var stateProvince: String?,
    @SerializedName("alpha_two_code") var alpha_two_code: String,
    @SerializedName("country") var country: String,
    @SerializedName("web_pages") var webPages: List<String> = ArrayList(),
    @SerializedName("domains") var domains: List<String> = ArrayList()
) : Parcelable