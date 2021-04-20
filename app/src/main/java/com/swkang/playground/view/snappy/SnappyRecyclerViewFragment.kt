package com.swkang.playground.view.snappy

import androidx.fragment.app.viewModels
import com.swkang.model.base.BaseViewModel
import com.swkang.model.domain.snappy.SnappyViewModel
import com.swkang.playground.R
import com.swkang.playground.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SnappyRecyclerViewFragment : BaseFragment(R.layout.snappy_fragment) {
    private val vm: SnappyViewModel by viewModels()

    override fun createViewModel(): BaseViewModel = vm
}