package com.example.usecases

import com.example.data.repository.CovidInfoRepository
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetCountryDetailsTest {

    @Mock
    lateinit var covidInfoRepository: CovidInfoRepository

    lateinit var getCountryDetailsByName: GetCountryDetailsByName

    @Before
    fun setUp() {
        getCountryDetailsByName = GetCountryDetailsByName(covidInfoRepository)
    }

    @Test
    fun `invoke calls covidInfo repository`() {
        runBlocking {

            val countryDetails = mockedCountryDetails.copy(id = 1)
            whenever(covidInfoRepository.getCountryDetailsByName("Argentina")).thenReturn(countryDetails)

            val result = getCountryDetailsByName.invoke("Argentina")

            Assert.assertEquals(countryDetails, result)
        }
    }
}