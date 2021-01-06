package com.swkang.playground.view.snappy

import com.swkang.playground.R
import com.swkang.playground.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SnappyRecyclerViewActivity : BaseActivity() {
    override fun getLayoutId(): Int = R.layout.snappy_activity
}