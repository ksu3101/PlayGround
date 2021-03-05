package com.swkang.playground.view.contracts

import androidx.fragment.app.viewModels
import com.swkang.model.base.BaseViewModel
import com.swkang.model.domain.contracts.ForResultFragmentVM
import com.swkang.playground.R
import com.swkang.playground.base.BaseFragment

class ForResultFragment: BaseFragment() {
    private val vm: ForResultFragmentVM by viewModels()

    override fun getLayoutId(): Int = R.layout.contracts_forresult_fragment

    override fun createViewModel(): BaseViewModel = vm
}