package com.example.usecases

import com.example.data.repository.CovidInfoRepository
import com.example.domain.CountryDetails

class GetCountryDetailsByName(private val covidInfoRepository: CovidInfoRepository) {
    suspend fun invoke(countryName: String): CountryDetails = covidInfoRepository.getCountryDetailsByName(countryName)
}