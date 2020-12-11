package com.swkang.playground.view.udftester

import com.swkang.model.base.BaseViewModel
import com.swkang.playground.R
import com.swkang.playground.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UdfTesterFragment : BaseFragment() {

    override fun getLayoutId(): Int = R.layout.udftest_fragment

    override fun createViewModel(): BaseViewModel {
    }
}