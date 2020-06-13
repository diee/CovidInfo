package com.example.covidinfo.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.covidinfo.FakeLocalDataSource
import com.example.covidinfo.defaultFakeCountryCases
import com.example.covidinfo.initMockedDi
import com.example.covidinfo.ui.main.MainViewModel
import com.example.data.source.LocalDataSource
import com.example.testshared.mockedCountryCases
import com.example.usecases.GetCountryCases
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.koin.test.get
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainIntegrationTests : AutoCloseKoinTest() {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var observer: Observer<MainViewModel.UiModel>

    private lateinit var vm: MainViewModel

    @Before
    fun setup() {
        val vmModule = module {
            factory { MainViewModel(get(), get()) }
            factory { GetCountryCases(get()) }
        }

        initMockedDi(vmModule)
        vm = get()
    }

    @Test
    fun `data is loaded from remote when local is empty `() {
        vm.model.observeForever(observer)

        vm.getCountryCases()

        verify(observer).onChanged(MainViewModel.UiModel.Content(defaultFakeCountryCases))
    }

    @Test
    fun `data is loaded from local when available`() {
        val fakeLocalCountryCases = listOf(mockedCountryCases.copy(id = 10), mockedCountryCases.copy(id = 11))
        val localDataSource = get<LocalDataSource>() as FakeLocalDataSource
        localDataSource.countryCasesList = fakeLocalCountryCases

        vm.model.observeForever(observer)
        vm.getCountryCases()
        verify(observer).onChanged(MainViewModel.UiModel.Content(fakeLocalCountryCases))
    }
}