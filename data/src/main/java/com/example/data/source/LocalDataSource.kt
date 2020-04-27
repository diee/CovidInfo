package com.example.data.source

import com.example.domain.CountryCases

interface LocalDataSource {
    suspend fun getCountryCases(): List<CountryCases>
    suspend fun saveCountryCases(list: List<CountryCases>)
    suspend fun isEmpty(): Boolean
}