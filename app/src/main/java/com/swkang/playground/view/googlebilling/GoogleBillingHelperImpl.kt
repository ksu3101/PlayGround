package com.swkang.playground.view.googlebilling

import android.app.Activity
import android.text.TextUtils
import android.util.Log
import com.android.billingclient.api.AcknowledgePurchaseParams
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingClientStateListener
import com.android.billingclient.api.BillingFlowParams
import com.android.billingclient.api.BillingResult
import com.android.billingclient.api.Purchase
import com.android.billingclient.api.PurchasesUpdatedListener
import com.android.billingclient.api.SkuDetails
import com.android.billingclient.api.SkuDetailsParams
import com.swkang.model.base.helper.BillingServiceReadyState
import com.swkang.model.base.helper.BillingTestItemRecivedState
import com.swkang.model.base.helper.GoogleBillingErrorException
import com.swkang.model.base.helper.GoogleBillingHelper
import com.swkang.model.base.helper.PurchaseConfirmedFinisedState
import com.swkang.model.base.helper.PurchaseItems
import com.swkang.model.base.helper.PurchaseState
import com.swkang.model.base.helper.PurchaseSuccessState
import com.swkang.playground.R
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject

class GoogleBillingHelperImpl(
    private val activity: Activity
) : GoogleBillingHelper {
    private val TAG = "GoogleBillingHelper"
    private val resultListener by lazy {
        PublishSubject.create<PurchaseState>()
    }

    private val purchaseupdatedListener = PurchasesUpdatedListener { billingResult, purchases ->
        logBillingResults(billingResult, "purchaseupdatedListener()", purchases)
        if (billingResult.responseCode == BillingClient.BillingResponseCode.OK && purchases != null) {
            for (purchase in purchases) {
                // 구매 완료 -> 구매 확인 처리
                Log.d(TAG, "\t\tpurchase = $purchase")
                resultListener.onNext(PurchaseSuccessState(billingResult, purchase))
            }
        } else {
            // 구매 중 오류
            resultListener.onError(
                GoogleBillingErrorException(
                    activity.getString(
                        if (billingResult.responseCode == BillingClient.BillingResponseCode.USER_CANCELED)
                            R.string.google_billing_usercanceled
                        else
                            R.string.google_billing_error
                    )
                )
            )
        }
    }

    private val billingClient = BillingClient.newBuilder(activity)
        .setListener(purchaseupdatedListener)
        .enablePendingPurchases()
        .build()

    init {
        billingClient.startConnection(object : BillingClientStateListener {
            override fun onBillingSetupFinished(billingResult: BillingResult) {
                if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                    resultListener.onNext(BillingServiceReadyState)
                } else {
                    resultListener.onError(
                        GoogleBillingErrorException(
                            activity.getString(R.string.google_billing_error_setup)
                        )
                    )
                }
            }

            override fun onBillingServiceDisconnected() {
                Log.e(TAG, "onBillingServiceDisconnected() called..")
            }
        })
    }

    override fun getPurchaseResultListener(): Observable<PurchaseState> =
        resultListener.hide().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())

    override fun isBillingClientReady(): Boolean {
        return billingClient.isReady
    }

    override fun queryTestItemSkuDetails() {
        val skuDetailParams = SkuDetailsParams.newBuilder().apply {
            setSkusList(listOf(PurchaseItems.GEM_100.skuId))
            setType(BillingClient.SkuType.INAPP)
        }.build()

        billingClient.querySkuDetailsAsync(skuDetailParams) { billingResult, skuDetailsList ->
            logBillingResults(billingResult, "queryTestingBillings() // ")
            resultListener.onNext(BillingTestItemRecivedState(skuDetailsList ?: emptyList()))
        }
    }

    override fun requestPurchaseItem(skuDetails: SkuDetails) {
        val flowParams = BillingFlowParams.newBuilder().apply {
            setSkuDetails(skuDetails)
        }.build()

        val responseCode = billingClient.launchBillingFlow(activity, flowParams).responseCode
        if (responseCode != BillingClient.BillingResponseCode.OK) {
            resultListener.onError(GoogleBillingErrorException(activity.getString(R.string.google_billing_error)))
        }
        // 여기부터 purchaseupdatedListener에서 처리.
    }

    override fun confirmPurchase(purchase: Purchase) {
        if (purchase.purchaseState == Purchase.PurchaseState.PURCHASED && !purchase.isAcknowledged) {
            val acknowledgePurchaseParams = AcknowledgePurchaseParams.newBuilder()
                .setPurchaseToken(purchase.purchaseToken)
            billingClient.acknowledgePurchase(acknowledgePurchaseParams.build()) { billingResult ->
                logBillingResults(billingResult, "confirmPurchase()")
                resultListener.onNext(PurchaseConfirmedFinisedState(billingResult))
            }
        }
        // 소비성 구매에 대한 consumable처리는 따로 해야 한다.
    }

    private fun logBillingResults(
        billingResult: BillingResult,
        moreTag: String? = null,
        purchases: List<Purchase>? = null
    ) {
        var logMessage = if (!TextUtils.isEmpty(moreTag)) "$moreTag // " else ""
        logMessage += "responseCode = ${billingResult.responseCode}, debugMessage = ${billingResult.debugMessage}" +
                purchases?.let {
                    ", purchases list size = ${it.size}"
                }
        Log.d(TAG, logMessage)
    }

}