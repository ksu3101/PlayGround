package com.swkang.playground.view.contracts

import androidx.fragment.app.viewModels
import com.swkang.model.base.BaseViewModel
import com.swkang.model.domain.contracts.StartContractsFragmentVM
import com.swkang.playground.R
import com.swkang.playground.base.BaseFragment

class StartContractsFragment: BaseFragment(R.layout.contracts_start_fragment) {
    private val vm: StartContractsFragmentVM by viewModels()

    override fun createViewModel(): BaseViewModel = vm
}