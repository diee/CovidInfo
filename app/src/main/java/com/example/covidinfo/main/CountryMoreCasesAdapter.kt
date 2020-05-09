package com.example.covidinfo.main

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.covidinfo.R
import com.example.covidinfo.common.basicDiffUtil
import com.example.covidinfo.common.inflate
import com.example.domain.CountryCases
import kotlinx.android.synthetic.main.country_cases_item.view.tvCountry
import kotlinx.android.synthetic.main.country_more_cases_item.view.*

class CountryMoreCasesAdapter(private val listener: (CountryCases) -> Unit) :
    RecyclerView.Adapter<CountryMoreCasesAdapter.ViewHolder>() {

    var countryCases: List<CountryCases> by basicDiffUtil(
        emptyList(),
        areItemsTheSame = { old, new -> old.id == new.id }
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.country_more_cases_item, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = countryCases.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val countryCase = countryCases[position]
        holder.bind(countryCase)
        holder.itemView.setOnClickListener { listener(countryCase) }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(countryCases: CountryCases) {
            itemView.tvCountry.text = countryCases.countryName
            itemView.tvTotalCasesValue.text = countryCases.cases
            itemView.tvCasesValue.text = countryCases.newCases
        }
    }
}