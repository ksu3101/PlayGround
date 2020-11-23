package com.swkang.playground.base.helper

import android.content.Context
import android.widget.Toast
import com.swkang.model.base.helper.MessageHelper

class MessageHelperImpl(
    private val context: Context
) : MessageHelper {

    override fun showToast(msgResId: Int, msgStr: String?) {
        val msg =
            if (msgResId == 0 && msgStr.isNullOrEmpty()) {
                throw IllegalArgumentException("message parameter has not available.")
            } else {
                if (!msgStr.isNullOrEmpty()) msgStr
                else context.getString(msgResId)
            }
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }
    
}