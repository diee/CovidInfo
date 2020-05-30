package com.example.data

import com.example.data.repository.CovidInfoRepository
import com.example.data.source.LocalDataSource
import com.example.data.source.RemoteDataSource
import com.example.domain.CountryCases
import com.example.domain.CountryDetails
import com.example.testshared.mockedCountryCases
import com.example.testshared.mockedCountryDetails
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CovidInfoRepositoryTest {

    @Mock
    lateinit var localDataSource: LocalDataSource

    @Mock
    lateinit var remoteDataSource: RemoteDataSource

    lateinit var covidInfoRepository: CovidInfoRepository

    @Before
    fun setUp() {
        covidInfoRepository = CovidInfoRepository(remoteDataSource, localDataSource)
    }

    @Test
    fun `getCountryCases gets from local data source first`() {
        runBlocking {

            val localCountryCases = listOf(mockedCountryCases.copy(1))
            whenever(localDataSource.isEmpty()).thenReturn(false)
            whenever(localDataSource.getCountryCases()).thenReturn(localCountryCases)

            val result = covidInfoRepository.getCountryCases()

            Assert.assertEquals(localCountryCases, result)
        }
    }

    @Test
    fun `getCountryCases saves remote data to local`() {
        runBlocking {

            val remoteCountryCases = listOf(mockedCountryCases.copy(2))
            whenever(localDataSource.isEmpty()).thenReturn(true)
            whenever(remoteDataSource.getCountryCases()).thenReturn(remoteCountryCases)

            covidInfoRepository.getCountryCases()

            verify(localDataSource).saveCountryCases(remoteCountryCases)
        }
    }

    @Test
    fun `findByName calls remote data source`() {
        runBlocking {

            val countryDetails = mockedCountryDetails.copy(id = 5)
            whenever(remoteDataSource.getCountryDetailsByName("Argentina")).thenReturn(countryDetails)

            val result = covidInfoRepository.getCountryDetailsByName("Argentina")

            Assert.assertEquals(countryDetails, result)
        }
    }
}