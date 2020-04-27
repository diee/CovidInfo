package com.example.covidinfo.data.database

import com.example.covidinfo.data.toCountryCasesEntity
import com.example.covidinfo.data.toDomainCountryCases
import com.example.data.source.LocalDataSource
import com.example.domain.CountryCases
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomDataSource(db: CovidInfoDataBase) : LocalDataSource {

    private val countryCasesDao = db.countryCasesDao()

    override suspend fun isEmpty() =
        withContext(Dispatchers.IO) { countryCasesDao.countryCasesCount() <= 0 }

    override suspend fun getCountryCases() =
        withContext(Dispatchers.IO) { countryCasesDao.getAll().map { it.toDomainCountryCases() } }

    override suspend fun saveCountryCases(list: List<CountryCases>) {
        withContext(Dispatchers.IO) { countryCasesDao.insertCountryCases(list.map { it.toCountryCasesEntity() }) }
    }
}
