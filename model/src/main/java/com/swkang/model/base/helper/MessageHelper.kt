package com.swkang.model.base.helper

import androidx.annotation.StringRes

interface MessageHelper {

    fun showToast(
        @StringRes msgResId: Int,
        msgStr: String? = null
    )

}