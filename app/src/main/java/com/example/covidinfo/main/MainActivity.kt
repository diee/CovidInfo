package com.example.covidinfo.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.covidinfo.R
import com.example.covidinfo.common.app
import com.example.covidinfo.common.getViewModel
import com.example.covidinfo.common.startActivity
import com.example.covidinfo.countrydetail.CountryDetailActivity
import com.example.covidinfo.data.database.RoomDataSource
import com.example.covidinfo.data.server.CovidInfoDataSource
import com.example.covidinfo.main.MainViewModel.UiModel
import com.example.data.repository.CovidInfoRepository
import com.example.usecases.GetCountryCases
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: CountryCasesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = getViewModel {
            MainViewModel(
                GetCountryCases(CovidInfoRepository(CovidInfoDataSource(), RoomDataSource(app.db)))
            )
        }

        adapter = CountryCasesAdapter(viewModel::onCountryCasesClicked)
        rvCountryCases.adapter = adapter
        viewModel.model.observe(this, Observer(::updateUi))
        viewModel.getCountryCases()
    }

    private fun updateUi(model: UiModel) {
        progress.visibility = if (model is UiModel.Loading) View.VISIBLE else View.GONE

        when (model) {
            is UiModel.Content -> adapter.countryCases = model.countryCases
            is UiModel.Navigation -> this.startActivity<CountryDetailActivity> {
                putExtra(CountryDetailActivity.COUNTRY_CASES, model.countryCases.countryName)
            }
        }
    }
}
