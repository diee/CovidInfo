package com.example.covidinfo.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CountryCasesEntity::class], version = 1)
abstract class CovidInfoDataBase : RoomDatabase(){

    abstract fun countryCasesDao(): CountryCasesDao
}