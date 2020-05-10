package com.example.covidinfo

import android.app.Application
import com.example.covidinfo.di.CovidInfoComponent
import com.example.covidinfo.di.DaggerCovidInfoComponent

class CovidInfoApp : Application() {

    lateinit var component: CovidInfoComponent
        private set

    override fun onCreate() {
        super.onCreate()

        component = DaggerCovidInfoComponent
            .factory()
            .create(this)
    }
}