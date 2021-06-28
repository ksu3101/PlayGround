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
import com.swkang.model.base.helper.BillingTestItemReceivedState
import com.swkang.model.base.helper.PurchaseException
import com.swkang.model.base.helper.GoogleBillingHelper
import com.swkang.model.base.helper.PurchaseConfirmedState
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
    private val TAG = "GoogleBillingModule //Helper"
    private val resultListener by lazy {
        PublishSubject.create<PurchaseState>()
    }

    private val purchaseUpdatedListener = PurchasesUpdatedListener { billingResult, purchases ->
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
                PurchaseException(
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
        .setListener(purchaseUpdatedListener)
        .enablePendingPurchases()
        .build()

    init {
        billingClient.startConnection(object : BillingClientStateListener {
            override fun onBillingSetupFinished(billingResult: BillingResult) {
                if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                    resultListener.onNext(BillingServiceReadyState)
                } else {
                    resultListener.onError(
                        PurchaseException(
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
            resultListener.onNext(BillingTestItemReceivedState(skuDetailsList ?: emptyList()))
        }
    }

    override fun requestPurchaseItem(skuDetails: SkuDetails) {
        Log.d(TAG, ">> requestPurchaseItem() // skuDetails = ${skuDetails}")
        val flowParams = BillingFlowParams.newBuilder().apply {
            setSkuDetails(skuDetails)
        }.build()

        val responseCode = billingClient.launchBillingFlow(activity, flowParams).responseCode
        Log.d(TAG, ">> requestPurchaseItem() // responseCode = [$responseCode] ${getResponseStringByCode(responseCode)}")
        if (responseCode != BillingClient.BillingResponseCode.OK) {
            resultListener.onError(PurchaseException(activity.getString(R.string.google_billing_error)))
        }
        // 여기부터 purchaseupdatedListener에서 처리.
    }

    override fun confirmPurchase(purchase: Purchase) {
        if (purchase.purchaseState == Purchase.PurchaseState.PURCHASED && !purchase.isAcknowledged) {
            val acknowledgePurchaseParams = AcknowledgePurchaseParams.newBuilder()
                .setPurchaseToken(purchase.purchaseToken)
            billingClient.acknowledgePurchase(acknowledgePurchaseParams.build()) { billingResult ->
                logBillingResults(billingResult, "confirmPurchase()")
                resultListener.onNext(PurchaseConfirmedState(billingResult))
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
        logMessage += "responseCode = [${billingResult.responseCode}] ${
            getResponseStringByCode(
                billingResult.responseCode
            )
        }, debugMessage = ${billingResult.debugMessage}" +
                purchases?.let {
                    ", purchases list size = ${it.size}"
                }
        Log.d(TAG, logMessage)
    }

    /**
     * @see com.android.billingclient.api.BillingClient.BillingResponseCode
     */
    private fun getResponseStringByCode(resCode: Int): String {
        return when (resCode) {
            -3 -> "SERVICE_TIMEOUT"
            -2 -> "FEATURE_NOT_SUPPORTED"
            -1 -> "SERVICE_DISCONNECTED"
            0 -> "OK"
            1 -> "USER_CANCELED"
            2 -> "SERVICE_UNAVAILABLE"
            3 -> "BILLING_UNAVAILABLE"
            4 -> "ITEM_UNAVAILABLE"
            5 -> "DEVELOPER_ERROR"
            6 -> "ERROR"
            7 -> "ITEM_ALREADY_OWNED"
            8 -> "ITEM_NOT_OWNED"
            else -> "UNKNOWN_RESPONSE_CODE"
        }
    }

}