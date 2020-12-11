package com.swkang.model.domain.udftester

import androidx.hilt.lifecycle.ViewModelInject
import com.swkang.model.base.BaseStateViewModel
import com.swkang.model.base.State

data class UdfTesterStateOne(
    val name: String
): State

class UdfTesterViewModel @ViewModelInject constructor(

): BaseStateViewModel<UdfTesterStateOne>() {

    override fun render(state: UdfTesterStateOne) {
    }

}