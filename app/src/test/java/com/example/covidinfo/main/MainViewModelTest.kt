package com.example.covidinfo.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.covidinfo.ui.main.MainViewModel
import com.example.covidinfo.ui.main.MainViewModel.UiModel
import com.example.testshared.mockedCountryCases
import com.example.usecases.GetCountryCases
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
class MainViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var getCountryCases: GetCountryCases

    @Mock
    lateinit var observer: Observer<UiModel>

    private lateinit var vm: MainViewModel

    @Before
    fun setUp() {
        vm = MainViewModel(getCountryCases, Dispatchers.Unconfined)
    }

    @Test
    fun `on getCountryCases, loading is shown`() {
        runBlocking {
            val countryCases = listOf(mockedCountryCases.copy(id = 1))
            whenever(getCountryCases.invoke()).thenReturn(countryCases)
            vm.model.observeForever(observer)

            vm.getCountryCases()

            verify(observer).onChanged(UiModel.Loading)
        }
    }

    @Test
    fun `after requesting getCountryCases, content is shown`() {
        runBlocking {
            val countryCases = listOf(mockedCountryCases.copy(id = 1))
            whenever(getCountryCases.invoke()).thenReturn(countryCases)
            vm.model.observeForever(observer)

            vm.getCountryCases()

            verify(observer).onChanged(UiModel.Content(countryCases))
        }
    }
}