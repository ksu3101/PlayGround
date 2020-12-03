package com.swkang.playground.base.databinding

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.swkang.common.exts.toCommaNumberString

@BindingAdapter("commaNumber")
fun numberToCommaString(tv: TextView, number: Number) {
    numberToCommaString(tv, number)
}

@BindingAdapter(value = ["commaNumber", "suffix"])
fun numberToCommaString(tv: TextView, number: Number, suffix: String? = null) {
    val convertedText = number.toCommaNumberString() + if (suffix.isNullOrEmpty()) "" else suffix
    tv.setText(convertedText)
}