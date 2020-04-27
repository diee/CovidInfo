package com.example.covidinfo.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CountryCasesDao {

    @Query("SELECT * FROM CountryCasesEntity WHERE countryName != ''")
    fun getAll(): List<CountryCasesEntity>

    @Query("SELECT COUNT(id) FROM CountryCasesEntity")
    fun countryCasesCount(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCountryCases(countryCasesList: List<CountryCasesEntity>)
}