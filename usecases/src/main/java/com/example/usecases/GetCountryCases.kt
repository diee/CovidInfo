package com.example.usecases

import com.example.data.repository.CovidInfoRepository
import com.example.domain.CountryCases

class GetCountryCases(private val covidInfoRepository: CovidInfoRepository) {
    suspend fun invoke(): List<CountryCases> = covidInfoRepository.getCountryCases()
}