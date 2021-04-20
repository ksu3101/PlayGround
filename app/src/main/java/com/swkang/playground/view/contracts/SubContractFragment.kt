package com.swkang.playground.view.contracts

import androidx.fragment.app.viewModels
import com.swkang.model.base.BaseViewModel
import com.swkang.model.domain.contracts.SubContractFragmentVM
import com.swkang.playground.R
import com.swkang.playground.base.BaseFragment

class SubContractFragment: BaseFragment(R.layout.contract_sub_fragment) {
    private val vm: SubContractFragmentVM by viewModels()

    override fun createViewModel(): BaseViewModel = vm
}