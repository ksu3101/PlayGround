package com.swkang.playground.view.covid19

import androidx.fragment.app.viewModels
import com.swkang.model.base.BaseViewModel
import com.swkang.model.domain.covid19.Covid19StatusViewModel
import com.swkang.playground.R
import com.swkang.playground.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Covid19StatusFragment : BaseFragment() {
    private val vm: Covid19StatusViewModel by viewModels()

    override fun getLayoutId(): Int = R.layout.covid19status_fragment

    override fun createViewModel(): BaseViewModel = vm
}