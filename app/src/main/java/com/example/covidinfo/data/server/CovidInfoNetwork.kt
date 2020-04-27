package com.example.covidinfo.data.server

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CovidInfoNetwork {

    private val okHttpClient = HttpLoggingInterceptor().run {
        level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder().addInterceptor(this).build()
    }

    val service: CovidInfoService = Retrofit.Builder()
        .baseUrl("https://coronavirus-monitor.p.rapidapi.com/coronavirus/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .run {
            create<CovidInfoService>(
                CovidInfoService::class.java
            )
        }
}