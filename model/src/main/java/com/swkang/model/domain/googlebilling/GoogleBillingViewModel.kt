package com.swkang.model.domain.googlebilling

import com.swkang.model.base.BaseViewModel
import com.swkang.model.base.helper.MessageHelper
import com.swkang.model.base.helper.PurchaseResultDatas
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GoogleBillingViewModel @Inject constructor(
    private val messageHelper: MessageHelper
) : BaseViewModel() {


    fun onGoogleBillingResultReceived(result: PurchaseResultDatas) {

    }

    fun onGoogleBillingErrorReceived(errorMessage: String) {
        messageHelper.showToast(errorMessage)
    }

}