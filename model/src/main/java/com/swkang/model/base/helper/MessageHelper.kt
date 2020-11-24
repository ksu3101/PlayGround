package com.swkang.model.base.helper

import androidx.annotation.StringRes

interface MessageHelper {

    fun showToast(
        @StringRes msgResId: Int,
        isLong: Boolean = false
    )

    fun showToast(
        msgStr: String,
        isLong: Boolean = false
    )

}