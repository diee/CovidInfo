package com.example.covidinfo.data.server

import com.example.covidinfo.data.toDomainCountryCases
import com.example.covidinfo.data.toDomainCountryDetails
import com.example.data.source.RemoteDataSource
import com.example.domain.CountryDetails

class CovidInfoDataSource : RemoteDataSource {

    override suspend fun getCountryCases(): List<com.example.domain.CountryCases> {
        return CovidInfoNetwork.service
            .listCountryCasesAsync()
            .countriesStats
            .map { it.toDomainCountryCases() }
    }

    override suspend fun getCountryDetailsByName(countryName: String): CountryDetails {
        return CovidInfoNetwork.service
            .getCountryDetailsAsync(countryName)
            .countryStats[0].toDomainCountryDetails()
    }
}