package com.swkang.playground.view.contracts

import androidx.fragment.app.viewModels
import com.swkang.model.base.BaseViewModel
import com.swkang.model.domain.contracts.ContractFragmentVM
import com.swkang.playground.R
import com.swkang.playground.base.BaseFragment

class ContractFragment: BaseFragment(R.layout.contract_sub_fragment) {
    private val vm: ContractFragmentVM by viewModels()

    override fun createViewModel(): BaseViewModel = vm
}