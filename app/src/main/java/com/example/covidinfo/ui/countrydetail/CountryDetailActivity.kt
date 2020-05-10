package com.example.covidinfo.ui.countrydetail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.covidinfo.R
import com.example.covidinfo.common.app
import com.example.covidinfo.common.getViewModel
import com.example.covidinfo.data.database.RoomDataSource
import com.example.covidinfo.data.server.CovidInfoDataSource
import com.example.data.repository.CovidInfoRepository
import com.example.usecases.GetCountryDetailsByName
import kotlinx.android.synthetic.main.activity_country_detail.*

class CountryDetailActivity : AppCompatActivity() {

    companion object {
        const val COUNTRY_CASES = "CountryDetailActivity:countryCases"
    }

    private lateinit var component: CountryDetailActivityComponent
    private val viewModel by lazy { getViewModel { component.countryDetailViewModel } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_detail)

        component = app.component
            .plus(CountryDetailActivityModule(intent.getStringExtra(COUNTRY_CASES) ?: ""))

        viewModel.model.observe(this, Observer(::updateUi))

        btnBack?.setOnClickListener { finish() }
    }

    private fun updateUi(model: CountryDetailViewModel.UiModel) {
        progress.visibility =
            if (model is CountryDetailViewModel.UiModel.Loading) View.VISIBLE else View.GONE

        when (model) {
            is CountryDetailViewModel.UiModel.Content -> {
                with(model.countryDetails) {
                    tvCountryName?.text = countryName
                    tvTotalCasesValue.text = cases
                    tvNewCasesValue.text = newCases
                    tvActiveCasesValue.text = activeCases
                    tvTotalRecoveredValue.text = totalRecovered
                    tvTotalTestsValue.text = totalTests
                    tvTotalDeathsValue.text = deaths
                }
            }
        }
    }
}
