package com.example.covidinfo.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.covidinfo.common.ScopedViewModel
import com.example.domain.CountryCases
import com.example.usecases.GetCountryCases
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class MainViewModel(
    private val getCountryCases: GetCountryCases,
    uiDispatcher: CoroutineDispatcher
) : ScopedViewModel(uiDispatcher) {

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            return _model
        }

    init {
        initScope()
    }

    sealed class UiModel {
        object Loading : UiModel()
        data class Content(val countryCases: List<CountryCases>) : UiModel()
        data class Navigation(val countryCases: CountryCases) : UiModel()
    }

    fun getCountryCases() {
        launch {
            _model.value = UiModel.Loading
            _model.value = UiModel.Content(getCountryCases.invoke())
        }
    }

    fun onCountryCasesClicked(countryCases: CountryCases) {
        _model.value = UiModel.Navigation(countryCases)
    }

    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }
}