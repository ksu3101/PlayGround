package com.swkang.playground.base.exts

import android.view.ViewGroup
import androidx.core.view.ViewCompat
import com.google.android.material.snackbar.Snackbar
import com.swkang.playground.R

fun Snackbar.setMaterialDesignStyle() {
    val params = this.view.layoutParams as ViewGroup.MarginLayoutParams
    params.setMargins(
        context.resources.getDimensionPixelSize(R.dimen.c_snackbar_margin_left),
        context.resources.getDimensionPixelSize(R.dimen.c_snackbar_margin_top),
        context.resources.getDimensionPixelSize(R.dimen.c_snackbar_margin_right),
        context.resources.getDimensionPixelSize(R.dimen.c_snackbar_margin_bottom)
    )
    this.view.layoutParams = params
    this.view.setPadding(
        context.resources.getDimensionPixelSize(R.dimen.c_snackbar_padding_left),
        context.resources.getDimensionPixelSize(R.dimen.c_snackbar_padding_top),
        context.resources.getDimensionPixelSize(R.dimen.c_snackbar_padding_right),
        context.resources.getDimensionPixelSize(R.dimen.c_snackbar_padding_bottom)
    )
    ViewCompat.setElevation(this.view, 6f)
}