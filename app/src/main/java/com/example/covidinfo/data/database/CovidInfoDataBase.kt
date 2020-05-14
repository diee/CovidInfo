package com.example.covidinfo.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CountryCasesEntity::class], version = 1)
abstract class CovidInfoDataBase : RoomDatabase(){

    companion object {
        fun build(context: Context) = Room.databaseBuilder(
            context,
            CovidInfoDataBase::class.java,
            "covidinfo-db"
        ).build()
    }

    abstract fun countryCasesDao(): CountryCasesDao
}