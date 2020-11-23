package com.swkang.playground.base.helper

import android.content.Context
import com.swkang.model.base.helper.ResourceHelper

class ResourceHelperImpl(
    private val context: Context
) : ResourceHelper {

    override fun getString(strResId: Int): String = context.getString(strResId)

    override fun getString(strResId: Int, vararg args: Any?): String =
        context.getString(strResId, *args)

}