package com.swkang.playground.view.main

import androidx.fragment.app.viewModels
import com.swkang.model.base.BaseViewModel
import com.swkang.model.domain.main.MainViewModel
import com.swkang.playground.R
import com.swkang.playground.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment(R.layout.main_fragment) {
    private val vm: MainViewModel by viewModels()

    override fun createViewModel(): BaseViewModel = vm
}