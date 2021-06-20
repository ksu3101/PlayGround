package com.swkang.playground.base.helper

import android.app.Activity
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingFlowParams
import com.android.billingclient.api.PurchasesUpdatedListener
import com.android.billingclient.api.SkuDetailsParams
import com.swkang.model.base.helper.GoogleBillingErrorException
import com.swkang.model.base.helper.GoogleBillingHelper
import com.swkang.model.base.helper.PurchaseResultDatas
import com.swkang.playground.R
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import java.lang.NullPointerException

class GoogleBillingHelperImpl(
    private val activity: Activity
) : GoogleBillingHelper {
    private val resultListener by lazy {
        PublishSubject.create<PurchaseResultDatas>()
    }

    private val purchaseupdatedListener = PurchasesUpdatedListener { billingResult, purchases ->
        if (billingResult.responseCode == BillingClient.BillingResponseCode.OK && purchases != null) {
            for (purchase in purchases) {
                resultListener.onNext(PurchaseResultDatas(billingResult, purchase))
            }
        } else {
            resultListener.onError(GoogleBillingErrorException(
                activity.getString(
                    if (billingResult.responseCode == BillingClient.BillingResponseCode.USER_CANCELED)
                        R.string.google_billing_usercanceled
                    else
                        R.string.google_billing_error
            )))
        }
    }

    private val billingClient = BillingClient.newBuilder(activity)
        .setListener(purchaseupdatedListener)
        .enablePendingPurchases()
        .build()

    override fun getPurchaseResultListener(): Observable<PurchaseResultDatas> =
        resultListener.hide().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())

    override fun queryTestingBillings() {
        val skuList = ArrayList<String>()
        skuList.add("")
        val params = SkuDetailsParams.newBuilder()
        params.setSkusList(skuList).setType(BillingClient.SkuType.INAPP)

        billingClient.querySkuDetailsAsync(params.build()) { billingResult, skuDetailsList ->
            // start flow
            val billingFlowParams = BillingFlowParams.newBuilder()
                .setSkuDetails(skuDetailsList?.get(0) ?: throw NullPointerException("WTF??"))
                .build()
            val responseCode = billingClient.launchBillingFlow(activity, billingFlowParams).responseCode

            // 결과 핸들링
        }
    }

}