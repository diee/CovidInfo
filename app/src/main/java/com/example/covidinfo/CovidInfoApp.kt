package com.example.covidinfo

import android.app.Application
import androidx.room.Room
import com.example.covidinfo.data.database.CovidInfoDataBase

class CovidInfoApp : Application() {

    lateinit var db: CovidInfoDataBase
        private set

    override fun onCreate() {
        super.onCreate()

        db = Room.databaseBuilder(
            this,
            CovidInfoDataBase::class.java, "covidinfo-db"
        ).build()
    }
}