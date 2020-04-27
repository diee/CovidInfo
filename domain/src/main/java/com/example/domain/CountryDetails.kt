package com.example.domain

data class CountryDetails(
    val id: Int,
    val countryName: String? = null,
    val cases: String? = null,
    val deaths: String? = null,
    val totalRecovered: String? = null,
    val newDeaths: String? = null,
    val newCases: String? = null,
    val activeCases: String? = null,
    val totalTests: String? = null,
    val seriousCritical: String? = null,
    val totalCasesPer1m: String? = null,
    val recordDate: String? = null,
    val totalTestsPer1m: String? = null
)