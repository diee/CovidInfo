package com.example.covidinfo.di

import android.app.Application
import com.example.covidinfo.ui.countrydetail.CountryDetailActivityComponent
import com.example.covidinfo.ui.countrydetail.CountryDetailActivityModule
import com.example.covidinfo.ui.main.MainActivityComponent
import com.example.covidinfo.ui.main.MainActivityModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DataModule::class])
interface CovidInfoComponent {

    fun plus(module: MainActivityModule): MainActivityComponent
    fun plus(module: CountryDetailActivityModule): CountryDetailActivityComponent

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): CovidInfoComponent
    }
}