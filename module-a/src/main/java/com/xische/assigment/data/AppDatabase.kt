package com.xische.assigment.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.xische.assigment.data.universities.local.UniversityDao
import com.xische.assigment.domain.university.entity.UniversityEntity

@Database(entities = [UniversityEntity::class], version = 1,exportSchema = false)
abstract class AppDatabase : RoomDatabase(){
    abstract fun universityDao() : UniversityDao
}