package com.example.covidinfo.data

import com.example.covidinfo.data.database.CountryCasesEntity
import com.example.covidinfo.data.server.CountryCasesResponse
import com.example.covidinfo.data.server.LatestCountryStats
import com.example.domain.CountryCases
import com.example.domain.CountryDetails


fun CountryCasesResponse.toDomainCountryCases(): CountryCases =
    CountryCases(
        0,
        countryName,
        cases,
        deaths,
        totalRecovered,
        newDeaths,
        newCases,
        activeCases,
        totalTests
    )

fun LatestCountryStats.toDomainCountryDetails(): CountryDetails =
    CountryDetails(
        0,
        countryName,
        totalCases,
        totalDeaths,
        totalRecovered,
        newDeaths,
        newCases,
        activeCases,
        totalTests,
        seriousCritical,
        totalCasesPer1m,
        recordDate,
        totalTestsPer1m
    )

fun CountryCasesEntity.toDomainCountryCases() =
    CountryCases(
        id,
        countryName,
        cases.toString(),
        deaths,
        totalRecovered,
        newDeaths,
        newCases,
        activeCases,
        totalTests
    )

fun CountryCases.toCountryCasesEntity() =
    CountryCasesEntity(
        0,
        countryName,
        cases.parseStringToInt(),
        deaths,
        totalRecovered,
        newDeaths,
        newCases,
        activeCases,
        totalTests
    )

fun String?.parseStringToInt(): Int {
    return this?.replace(",", "")?.toInt() ?: 0
}
