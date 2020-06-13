package com.example.covidinfo.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.covidinfo.initMockedDi
import com.example.covidinfo.ui.countrydetail.CountryDetailViewModel
import com.example.testshared.mockedCountryDetails
import com.example.usecases.GetCountryDetailsByName
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.koin.test.get
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CountryDetailsIntegrationTests : AutoCloseKoinTest() {

    @get: Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var observer: Observer<CountryDetailViewModel.UiModel>

    lateinit var vm: CountryDetailViewModel

    @Before
    fun setup() {
        val vmModule = module {
            factory { (country: String) -> CountryDetailViewModel(country, get(), get()) }
            factory { GetCountryDetailsByName(get()) }
        }

        initMockedDi(vmModule)
        vm = get { parametersOf("Argentina") }
    }

    @Test
    fun `observing LiveData finds the CountryDetails`() {
        vm.model.observeForever(observer)
        verify(observer).onChanged(
            CountryDetailViewModel.UiModel.Content(mockedCountryDetails.copy(countryName = "Argentina"))
        )
    }
}