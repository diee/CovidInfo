package com.example.covidinfo.data.server

import com.google.gson.annotations.SerializedName

data class CountryCasesResult(
    @SerializedName("countries_stat") val countriesStats: List<CountryCasesResponse>
)

data class CountryCasesResponse(
    @SerializedName("country_name") val countryName: String,
    @SerializedName("cases") val cases: String? = null,
    @SerializedName("deaths") val deaths: String? = null,
    @SerializedName("total_recovered") val totalRecovered: String? = null,
    @SerializedName("new_deaths") val newDeaths: String? = null,
    @SerializedName("new_cases") val newCases: String? = null,
    @SerializedName("active_cases") val activeCases: String? = null,
    @SerializedName("total_tests") val totalTests: String? = null
)
