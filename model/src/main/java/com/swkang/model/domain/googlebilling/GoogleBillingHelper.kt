package com.swkang.model.base.helper

import com.android.billingclient.api.BillingResult
import com.android.billingclient.api.Purchase
import io.reactivex.rxjava3.core.Observable
import java.lang.RuntimeException

interface GoogleBillingHelper {

    fun getPurchaseResultListener(): Observable<PurchaseResultDatas>

    fun queryTestingBillings()

}

/**
 * 인앱 결제 등록된 아이템
 *
 * skuId를 string resource로 빼야 할까??
 */
enum class PurchaseItems(val skuId: String) {
    GEM_100("gem_100")
}

/**
 * 인앱 결제의 결과 데이터 클래스
 */
data class PurchaseResultDatas(
    val billingResult: BillingResult,
    val purchase: Purchase
)

/**
 * 인앱 결제 중 발생한 예외 클래스
 */
class GoogleBillingErrorException(message: String): RuntimeException(message)