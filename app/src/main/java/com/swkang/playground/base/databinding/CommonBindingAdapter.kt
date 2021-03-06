package com.swkang.playground.base.databinding

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide

@BindingAdapter("android:visibility")
fun setVisibility(v: View, visibility: Boolean) {
    v.visibility = if (visibility) View.VISIBLE else View.GONE
}

@BindingAdapter("imageUrl")
fun loadImageUrl(iv: ImageView, url: String?) {
    if (url.isNullOrEmpty()) {
        iv.setImageDrawable(null)
    } else {
        Glide.with(iv.context)
            .load(url)
            .into(iv)
    }
}

@BindingAdapter("android:onClick")
fun onViewClicked(v: View, onClickListener: () -> Unit) {
    v.setOnClickListener { onClickListener() }
}

@BindingAdapter("onRefresh")
fun onSwipeRefreshListener(v: SwipeRefreshLayout, onRefreshListener: () -> Unit) {
    v.setOnRefreshListener {
        onRefreshListener()
    }
}

@BindingAdapter("isLoading")
fun onSwipeRefreshLoadCompleteListener(v: SwipeRefreshLayout, isLoading: Boolean) {
    if (v.isRefreshing && !isLoading) {
        v.isRefreshing = false
    }
}