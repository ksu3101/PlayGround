package com.swkang.model.domain.covid19

import com.swkang.model.base.BaseViewModel
import com.swkang.model.base.helper.MessageHelper
import com.swkang.model.domain.covid19.repository.Covid19Repository
import javax.inject.Inject

class Covid19StatusViewModel @Inject constructor(
    val covid19Repo: Covid19Repository,
    val messageHelper: MessageHelper
) : BaseViewModel() {

    init {

    }

}