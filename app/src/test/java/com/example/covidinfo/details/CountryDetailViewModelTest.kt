package com.example.covidinfo.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.covidinfo.ui.countrydetail.CountryDetailViewModel
import com.example.testshared.mockedCountryDetails
import com.example.usecases.GetCountryDetailsByName
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class CountryDetailViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var getCountryDetailsByName: GetCountryDetailsByName

    @Mock
    lateinit var observer: Observer<CountryDetailViewModel.UiModel>

    private lateinit var vm: CountryDetailViewModel

    @Before
    fun setUp() {
        vm = CountryDetailViewModel("Argentina", getCountryDetailsByName, Dispatchers.Unconfined)
    }

    @Test
    fun `observing LiveData finds the movie`() {
        runBlocking {
            val countryDetails = mockedCountryDetails.copy(id = 1)
            whenever(getCountryDetailsByName.invoke("Argentina")).thenReturn(countryDetails)

            vm.model.observeForever(observer)

            verify(observer).onChanged(CountryDetailViewModel.UiModel.Content(countryDetails))
        }
    }
}