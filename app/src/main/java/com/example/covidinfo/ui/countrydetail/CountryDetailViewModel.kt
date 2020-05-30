package com.example.covidinfo.ui.countrydetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.covidinfo.common.ScopedViewModel
import com.example.domain.CountryDetails
import com.example.usecases.GetCountryDetailsByName
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class CountryDetailViewModel(
    private val countryName: String,
    private val getCountryDetailsByName: GetCountryDetailsByName,
    uiDispatcher: CoroutineDispatcher
) : ScopedViewModel(uiDispatcher) {

    sealed class UiModel {
        object Loading : UiModel()
        data class Content(val countryDetails: CountryDetails) : UiModel()
    }

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            if (_model.value == null) getCountryDetails()
            return _model
        }

    private fun getCountryDetails() = launch {
        _model.value = UiModel.Content(getCountryDetailsByName.invoke(countryName))
    }
}