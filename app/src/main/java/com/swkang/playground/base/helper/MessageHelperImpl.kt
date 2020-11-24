package com.swkang.playground.base.helper

import android.content.Context
import android.widget.Toast
import com.swkang.model.base.helper.MessageHelper

class MessageHelperImpl(
    private val context: Context
) : MessageHelper {
    private var toast: Toast? = null

    override fun showToast(msgResId: Int, isLong: Boolean) {
        showToast(msgResId, null, isLong)
    }

    override fun showToast(msgStr: String, isLong: Boolean) {
        showToast(0, msgStr, isLong)
    }

    private fun showToast(msgResId: Int, msgStr: String?, isLong: Boolean) {
        this.toast?.cancel()
        val msg =
            if (msgResId == 0 && msgStr.isNullOrEmpty()) {
                throw IllegalArgumentException("message parameter has not available.")
            } else {
                if (!msgStr.isNullOrEmpty()) msgStr
                else context.getString(msgResId)
            }
        val toastLength = if (isLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT
        this.toast = Toast.makeText(context, msg, toastLength)
        this.toast?.show()
    }

}