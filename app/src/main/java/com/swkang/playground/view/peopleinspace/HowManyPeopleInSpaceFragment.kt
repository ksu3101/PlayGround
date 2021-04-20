package com.swkang.playground.view.peopleinspace

import androidx.fragment.app.viewModels
import com.swkang.model.base.BaseViewModel
import com.swkang.model.domain.peopleinspace.HowManyPeopleInSpaceViewModel
import com.swkang.playground.R
import com.swkang.playground.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HowManyPeopleInSpaceFragment : BaseFragment(R.layout.howmanypeopleinspace_fragment) {
    private val vm: HowManyPeopleInSpaceViewModel by viewModels()

    override fun createViewModel(): BaseViewModel = vm
}