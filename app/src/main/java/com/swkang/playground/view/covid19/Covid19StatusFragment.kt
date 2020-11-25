package com.swkang.playground.view.covid19

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.swkang.model.base.BaseViewModel
import com.swkang.model.domain.covid19.Covid19StatusViewModel
import com.swkang.playground.R
import com.swkang.playground.base.BaseFragment

class Covid19StatusFragment(
    private val isKorea: Boolean
) : BaseFragment() {
    private val vm: Covid19StatusViewModel by viewModels()

    override fun getLayoutId(): Int = R.layout.covid19status_fragment

    override fun createViewModel(): BaseViewModel = vm

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = super.onCreateView(inflater, container, savedInstanceState)
        vm.setCountryAndStartRequesting(isKorea)
        return rootView
    }
}