package com.swkang.playground.view.covid19

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.swkang.model.domain.covid19.Covid19StatusViewModel
import com.swkang.model.domain.covid19.datas.LocationCovid19Infos
import com.swkang.playground.R

@BindingAdapter("covid19Country")
fun setCovid19CountryTitle(tv: TextView, isKorea: Boolean) {
    tv.text =
        tv.context.getString(
            if (isKorea) R.string.covid19_status_korea_title
            else R.string.covid19_status_world_title
        )
}

@BindingAdapter(value = ["viewModel", "covid19Locations"])
fun setUpCovid19StatusRecyclerView(
    rv: RecyclerView,
    vm: Covid19StatusViewModel,
    locations: List<LocationCovid19Infos>
) {
    val adapter = Covid19StatusListAdapter(vm)
    rv.adapter = adapter
    adapter.submitList(locations)
}