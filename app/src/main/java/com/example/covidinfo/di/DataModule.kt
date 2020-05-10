package com.example.covidinfo.di

import com.example.data.repository.CovidInfoRepository
import com.example.data.source.LocalDataSource
import com.example.data.source.RemoteDataSource
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun covidInfoRepositoryProvider(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource
    ) = CovidInfoRepository(remoteDataSource, localDataSource)
}