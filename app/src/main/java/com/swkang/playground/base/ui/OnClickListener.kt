package com.swkang.playground.base.ui

import android.view.View
import androidx.annotation.CallSuper

interface OnClickListener : View.OnClickListener {
    fun onClicked(view: View)

    @CallSuper
    override fun onClick(view: View?) {
        if (view == null) return
        onClicked(view)
    }
}