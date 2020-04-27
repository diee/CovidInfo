package com.example.data.source

import com.example.domain.CountryCases
import com.example.domain.CountryDetails

interface RemoteDataSource {
    suspend fun getCountryCases(): List<CountryCases>
    suspend fun getCountryDetailsByName(countryName: String): CountryDetails
}