package com.example.covidinfo.ui.main

import com.example.data.repository.CovidInfoRepository
import com.example.usecases.GetCountryCases
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Module
class MainActivityModule {

    @Provides
    fun mainViewModelProvider(getCountryCases: GetCountryCases) = MainViewModel(getCountryCases)

    @Provides
    fun getCountryCasesProvider(covidInfoRepository: CovidInfoRepository) =
        GetCountryCases(covidInfoRepository)

}

@Subcomponent(modules = [(MainActivityModule::class)])
interface MainActivityComponent {
    val mainViewModel: MainViewModel
}