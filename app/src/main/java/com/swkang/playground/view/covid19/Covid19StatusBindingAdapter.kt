package com.swkang.playground.view.covid19

import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.swkang.common.exts.toCommaNumberString
import com.swkang.model.domain.covid19.Covid19StatusViewModel
import com.swkang.model.domain.covid19.datas.LocationCovid19Infos
import com.swkang.playground.R

@BindingAdapter("newCaseValue")
fun setTextColorIfHasValue(tv: TextView, value: Long?) {
    if (value == null) {
        tv.text = ""
        tv.visibility = View.GONE
    } else {
        tv.setTextColor(
            ContextCompat.getColor(
                tv.context,
                if (value <= 0) R.color.covid_font_positive
                else R.color.covid_font_negative
            )
        )
        tv.text = if (value > 0) value.toCommaNumberString() else "0"
        tv.visibility = View.VISIBLE
    }
}

@BindingAdapter(value = ["viewModel", "covid19Locations"])
fun setUpCovid19StatusRecyclerView(
    rv: RecyclerView,
    vm: Covid19StatusViewModel,
    locations: List<LocationCovid19Infos>
) {
    var adapter = rv.adapter
    if (adapter == null || adapter !is Covid19StatusListAdapter) {
        adapter = Covid19StatusListAdapter(vm)
        rv.adapter = adapter
    }
    adapter.submitList(locations)
}