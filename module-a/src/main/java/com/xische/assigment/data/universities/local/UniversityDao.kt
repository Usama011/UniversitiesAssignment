package com.xische.assigment.data.universities.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.xische.assigment.domain.university.entity.UniversityEntity
import dagger.Provides

@Dao
interface UniversityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(university: UniversityEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(universities: List<UniversityEntity>)

    @Query("SELECT * FROM universities")
    fun findAll() : List<UniversityEntity>

    @Query("DELETE FROM universities")
    fun deleteAll()
}