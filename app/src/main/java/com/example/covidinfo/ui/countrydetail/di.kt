package com.example.covidinfo.ui.countrydetail

import com.example.data.repository.CovidInfoRepository
import com.example.usecases.GetCountryDetailsByName
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Module
class CountryDetailActivityModule(private val countryName: String) {

    @Provides
    fun countryDetailViewModel(getCountryDetailsByName: GetCountryDetailsByName) =
        CountryDetailViewModel(countryName, getCountryDetailsByName)

    @Provides
    fun getCountryDetailsByNameProvider(covidInfoRepository: CovidInfoRepository) = GetCountryDetailsByName(covidInfoRepository)
}

@Subcomponent(modules = [(CountryDetailActivityModule::class)])
interface CountryDetailActivityComponent {
    val countryDetailViewModel: CountryDetailViewModel
}