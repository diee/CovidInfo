package com.example.covidinfo

import com.example.data.source.LocalDataSource
import com.example.data.source.RemoteDataSource
import com.example.domain.CountryCases
import com.example.testshared.mockedCountryCases
import com.example.testshared.mockedCountryDetails
import kotlinx.coroutines.Dispatchers
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

fun initMockedDi(vararg modules: Module) {
    startKoin { modules(listOf(mockedAppModule, dataModule) + modules) }

}

private val mockedAppModule = module {
    single<LocalDataSource> { FakeLocalDataSource() }
    single<RemoteDataSource> { FakeRemoteDataSource() }
    single { Dispatchers.Unconfined }
}

val defaultFakeCountryCases = listOf(
    mockedCountryCases.copy(id = 1),
    mockedCountryCases.copy(id = 2),
    mockedCountryCases.copy(id = 3),
    mockedCountryCases.copy(id = 4),
    mockedCountryCases.copy(id = 5)
)

class FakeLocalDataSource : LocalDataSource {

    var countryCasesList = emptyList<CountryCases>()

    override suspend fun getCountryCases(): List<CountryCases> = countryCasesList

    override suspend fun saveCountryCases(list: List<CountryCases>) {
        countryCasesList = list
    }

    override suspend fun isEmpty() = countryCasesList.isEmpty()

}

class FakeRemoteDataSource : RemoteDataSource {

    var countryCasesList = defaultFakeCountryCases

    var countryCasesDetails = mockedCountryDetails.copy(countryName = "Argentina")

    override suspend fun getCountryCases(): List<CountryCases> = countryCasesList

    override suspend fun getCountryDetailsByName(countryName: String) = countryCasesDetails

}