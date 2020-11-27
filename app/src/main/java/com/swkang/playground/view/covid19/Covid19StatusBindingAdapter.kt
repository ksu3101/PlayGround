package com.swkang.playground.view.covid19

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.swkang.playground.R

@BindingAdapter("covid19Country")
fun setCovid19CountryTitle(tv: TextView, isKorea: Boolean) {
    tv.text =
        tv.context.getString(
            if (isKorea) R.string.covid19_status_korea_title
            else R.string.covid19_status_world_title
        )
}