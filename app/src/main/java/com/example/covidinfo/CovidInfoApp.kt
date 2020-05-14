package com.example.covidinfo

import android.app.Application

class CovidInfoApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initDI()
    }
}