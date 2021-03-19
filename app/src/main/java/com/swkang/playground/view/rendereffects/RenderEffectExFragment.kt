package com.swkang.playground.view.rendereffects

import androidx.fragment.app.viewModels
import com.swkang.model.base.BaseViewModel
import com.swkang.model.domain.rendereffects.RenderEffectFragmentVM
import com.swkang.playground.R
import com.swkang.playground.base.BaseFragment

class RenderEffectExFragment : BaseFragment(R.layout.rendereffect_fragment) {
    private val vm: RenderEffectFragmentVM by viewModels()

    override fun createViewModel(): BaseViewModel = vm
}