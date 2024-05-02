package com.xische.assigment.domain.university.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.xische.assigment.utils.Converters
import kotlinx.parcelize.Parcelize


@Entity(tableName = "universities", indices = [Index(value = ["name"], unique = true)])
@TypeConverters(value = [Converters::class])
@Parcelize
data class UniversityEntity(
    @PrimaryKey(autoGenerate = false)
    var name : String,
    @ColumnInfo(name = "stateProvince")
    var stateProvince: String,
    @ColumnInfo(name = "country")
    var country: String,
    @ColumnInfo(name = "alpha_two_code")
    var alpha_two_code: String,
    @ColumnInfo(name = "web_pages")
    var web_pages : List<String>,
    @ColumnInfo(name = "domains")
    var domains : List<String>
):Parcelable