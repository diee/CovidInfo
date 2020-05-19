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
class GetCountryCasesTest {

    @Mock
    lateinit var covidInfoRepository: CovidInfoRepository

    lateinit var getCountryCases: GetCountryCases

    @Before
    fun setUp() {
        getCountryCases = GetCountryCases(covidInfoRepository)
    }

    @Test
    fun `invoke calls covidInfo repository`() {
        runBlocking {

            val countryCases = listOf(mockedCountryCases.copy(id = 1))
            whenever(covidInfoRepository.getCountryCases()).thenReturn(countryCases)

            val result = getCountryCases.invoke()

            Assert.assertEquals(countryCases, result)
        }
    }
}