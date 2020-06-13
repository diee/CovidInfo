package com.example.covidinfo

import android.app.Application
import com.example.covidinfo.data.database.CovidInfoDataBase
import com.example.covidinfo.data.database.RoomDataSource
import com.example.covidinfo.data.server.CovidInfoDataSource
import com.example.covidinfo.data.server.CovidInfoNetwork
import com.example.covidinfo.ui.countrydetail.CountryDetailActivity
import com.example.covidinfo.ui.countrydetail.CountryDetailViewModel
import com.example.covidinfo.ui.main.MainActivity
import com.example.covidinfo.ui.main.MainViewModel
import com.example.data.repository.CovidInfoRepository
import com.example.data.source.LocalDataSource
import com.example.data.source.RemoteDataSource
import com.example.usecases.GetCountryCases
import com.example.usecases.GetCountryDetailsByName
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

fun Application.initDI() {
    startKoin {
        androidLogger()
        androidContext(this@initDI)
        modules(listOf(appModule, dataModule, scopesModule))
    }
}

val appModule = module {
    single { CovidInfoDataBase.build(get()) }
    factory<LocalDataSource> { RoomDataSource(get()) }
    factory<RemoteDataSource> { CovidInfoDataSource(get()) }
    single<CoroutineDispatcher> { Dispatchers.Main }
    single(named("baseUrl")) { "https://coronavirus-monitor.p.rapidapi.com/coronavirus/" }
    single { CovidInfoNetwork(get(named("baseUrl"))) }

}

val dataModule = module {
    factory { CovidInfoRepository(get(), get()) }
}

val scopesModule = module {
    scope(named<MainActivity>()) {
        viewModel { MainViewModel(get(), get()) }
        scoped { GetCountryCases(get()) }
    }

    scope(named<CountryDetailActivity>()) {
        viewModel { (country: String) -> CountryDetailViewModel(country, get(), get()) }
        scoped { GetCountryDetailsByName(get()) }
    }
}