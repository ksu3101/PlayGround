package com.swkang.model.base.helper

import com.android.billingclient.api.BillingResult
import com.android.billingclient.api.Purchase
import io.reactivex.rxjava3.core.Observable
import java.lang.RuntimeException

interface GoogleBillingHelper {

    fun getPurchaseResultListener(): Observable<PurchaseResultDatas>

    fun queryTestingBillings()

}

data class PurchaseResultDatas(
    val billingResult: BillingResult,
    val purchase: Purchase
)

class GoogleBillingErrorException(message: String): RuntimeException(message)