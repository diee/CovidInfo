package com.example.data.repository

import com.example.data.source.LocalDataSource
import com.example.data.source.RemoteDataSource
import com.example.domain.CountryCases
import com.example.domain.CountryDetails

class CovidInfoRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {

    suspend fun getCountryCases(): List<CountryCases> {
        if (localDataSource.isEmpty()) {
            val countryCasesList = remoteDataSource.getCountryCases()
            localDataSource.saveCountryCases(countryCasesList)
        }
        return localDataSource.getCountryCases()
    }

    suspend fun getCountryDetailsByName(countryName: String): CountryDetails {
        return remoteDataSource.getCountryDetailsByName(countryName)
    }
}
