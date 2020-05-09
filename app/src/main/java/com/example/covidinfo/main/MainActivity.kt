package com.example.covidinfo.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
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
import com.example.domain.CountryCases
import com.example.usecases.GetCountryCases
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.country_more_cases_component.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: CountryCasesAdapter
    private lateinit var moreCasesAdapter: CountryMoreCasesAdapter

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
        moreCasesAdapter = CountryMoreCasesAdapter(viewModel::onCountryCasesClicked)
        rvCountryCasesMoreCases.adapter = moreCasesAdapter
        viewModel.model.observe(this, Observer(::updateUi))
        viewModel.getCountryCases()

        observeInput()
    }

    private fun updateUi(model: UiModel) {
        progress.visibility = if (model is UiModel.Loading) View.VISIBLE else View.GONE

        when (model) {
            is UiModel.Content -> {
                tvCountryCasesTitle?.visibility = View.VISIBLE
                moreCasesComponent?.visibility = View.VISIBLE
                adapter.countryCases = model.countryCases
                moreCasesAdapter.countryCases = filterTopMoreCases(model.countryCases)
                adapter.filter.filter("")
            }
            is UiModel.Navigation -> this.startActivity<CountryDetailActivity> {
                putExtra(CountryDetailActivity.COUNTRY_CASES, model.countryCases.countryName)
            }
        }
    }

    private fun filterTopMoreCases(countryCases: List<CountryCases>): List<CountryCases> {
        return countryCases.sortedBy { it.cases }.takeLast(TOP_COUNTRY_CASES_QUANTITY)
    }

    private fun observeInput() {
        etSearchCountryCases.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                moreCasesComponent.visibility =
                    if (newText.isNullOrEmpty()) View.VISIBLE else View.GONE
                return false
            }
        })
    }

    companion object {
        const val TOP_COUNTRY_CASES_QUANTITY = 5
    }
}
