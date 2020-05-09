package com.example.covidinfo.main

import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.covidinfo.R
import com.example.covidinfo.common.basicDiffUtil
import com.example.covidinfo.common.inflate
import com.example.domain.CountryCases
import kotlinx.android.synthetic.main.country_cases_item.view.*

class CountryCasesAdapter(private val listener: (CountryCases) -> Unit) :
    RecyclerView.Adapter<CountryCasesAdapter.ViewHolder>(), Filterable {

    var countryCases: List<CountryCases> by basicDiffUtil(
        emptyList(),
        areItemsTheSame = { old, new -> old.id == new.id }
    )

    var searchableList = countryCases.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.country_cases_item, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = searchableList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val countryCase = searchableList[position]
        holder.bind(countryCase)
        holder.itemView.setOnClickListener { listener(countryCase) }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                searchableList = if (charSearch.isEmpty()) {
                    countryCases.toMutableList()
                } else {
                    val resultList = ArrayList<CountryCases>()
                    for (row in countryCases) {
                        if (row.countryName.contains(charSearch, true)) {
                            resultList.add(row)
                        }
                    }
                    resultList
                }
                val filterResults = FilterResults()
                filterResults.values = searchableList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                searchableList = results?.values as ArrayList<CountryCases>
                notifyDataSetChanged()
            }

        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(countryCases: CountryCases) {
            itemView.tvCountry.text = countryCases.countryName
            itemView.tvDeathsValue.text = countryCases.deaths
            itemView.tvCasesValue.text = countryCases.cases
            itemView.tvRecoveredValue.text = countryCases.totalRecovered
        }
    }
}