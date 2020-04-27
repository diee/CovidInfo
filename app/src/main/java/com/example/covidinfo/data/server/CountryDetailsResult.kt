package com.example.covidinfo.data.server

import com.google.gson.annotations.SerializedName

data class CountryDetailsResult(
    @SerializedName("country") val country: String,
    @SerializedName("latest_stat_by_country") val countryStats: List<LatestCountryStats>
)

data class LatestCountryStats(
    @SerializedName("id") val id: String? = null,
    @SerializedName("country_name") val countryName: String? = null,
    @SerializedName("total_cases") val totalCases: String? = null,
    @SerializedName("new_cases") val newCases: String? = null,
    @SerializedName("active_cases") val activeCases: String? = null,
    @SerializedName("total_deaths") val totalDeaths: String? = null,
    @SerializedName("new_deaths") val newDeaths: String? = null,
    @SerializedName("total_recovered") val totalRecovered: String? = null,
    @SerializedName("serious_critical") val seriousCritical: String? = null,
    @SerializedName("total_cases_per1m") val totalCasesPer1m: String? = null,
    @SerializedName("record_date") val recordDate: String? = null,
    @SerializedName("deaths_per1m") val deathsPer1m: String? = null,
    @SerializedName("total_tests") val totalTests: String? = null,
    @SerializedName("total_tests_per1m") val totalTestsPer1m: String? = null
)

