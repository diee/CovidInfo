package com.example.covidinfo.data.server

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface CovidInfoService {
    @Headers("x-rapidapi-key: e1vQaiZFwgmsh9m9Z7w926fiVLlUp1UQ9cSjsnEOXGbFDCMKIN")
    @GET("cases_by_country.php")
    suspend fun listCountryCasesAsync(): CountryCasesResult

    @Headers("x-rapidapi-key: e1vQaiZFwgmsh9m9Z7w926fiVLlUp1UQ9cSjsnEOXGbFDCMKIN")
    @GET("latest_stat_by_country.php")
    suspend fun getCountryDetailsAsync(@Query("country") countryName: String ): CountryDetailsResult
}