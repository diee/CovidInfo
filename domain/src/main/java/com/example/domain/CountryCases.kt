package com.example.domain

data class CountryCases(
    val id: Int,
    val countryName: String,
    val cases: String? = null,
    val deaths: String? = null,
    val totalRecovered: String? = null,
    val newDeaths: String? = null,
    val newCases: String? = null,
    val activeCases: String? = null,
    val totalTests: String? = null
)
