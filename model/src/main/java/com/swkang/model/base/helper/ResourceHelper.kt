package com.swkang.model.base.helper

import androidx.annotation.StringRes

interface ResourceHelper {

    fun getString(@StringRes strResId: Int): String

    fun getString(@StringRes strResId: Int, vararg args: Any?): String

    fun convertToDp(px: Float): Float

    fun convertToPx(dip: Float): Float

}