package com.example.covidinfo.di

import android.app.Application
import androidx.room.Room
import com.example.covidinfo.data.database.CovidInfoDataBase
import com.example.covidinfo.data.database.RoomDataSource
import com.example.covidinfo.data.server.CovidInfoDataSource
import com.example.data.source.LocalDataSource
import com.example.data.source.RemoteDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun databaseProvider(app: Application) =
        Room.databaseBuilder(app, CovidInfoDataBase::class.java, "covidinfo-db").build()

    @Provides
    fun localDataSourceProvider(db: CovidInfoDataBase): LocalDataSource = RoomDataSource(db)

    @Provides
    fun serverDataSourceProvider(): RemoteDataSource = CovidInfoDataSource()
}