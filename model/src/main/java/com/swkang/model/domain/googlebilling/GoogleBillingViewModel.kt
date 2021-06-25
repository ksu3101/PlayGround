package com.swkang.model.domain.googlebilling

import android.util.Log
import com.swkang.model.base.BaseViewModel
import com.swkang.model.base.helper.MessageHelper
import com.swkang.model.base.helper.PurchaseState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GoogleBillingViewModel @Inject constructor(
    private val messageHelper: MessageHelper
) : BaseViewModel() {

    fun purchaseResultHandler(result: PurchaseState) {
        Log.d(
            "GoogleBillingViewModel",
            ">>> purchaseResultHandler() result received. ${result.javaClass.name}"
        )

        // TODO : handle results
    }

    fun onGoogleBillingErrorReceived(errorMessage: String) {
        messageHelper.showToast(errorMessage)
    }

}