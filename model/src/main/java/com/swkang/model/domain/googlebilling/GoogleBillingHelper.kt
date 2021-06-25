package com.swkang.model.base.helper

import com.android.billingclient.api.BillingResult
import com.android.billingclient.api.Purchase
import com.android.billingclient.api.SkuDetails
import com.swkang.model.base.State
import io.reactivex.rxjava3.core.Observable
import java.lang.RuntimeException

/**
 * 구글 인앱 결제 모듈
 *
 * 1. GoogleBillingHelper를 구현한 클래스의 인스턴스 초기화시 내부 billingClient가 초기화 되며
 * 구글 인앱을 사용할 수 있는지 여부를 resultListener를 통해 반환 한다.
 *
 * 2. 문제 없이 구글 인앱 결제를 사용할 수 있다면 상품의 sku정보를 가져온다.
 *
 * 3. 상품의 sku정보를 가져왔다면 해당 `SkuDetails`를 이용해서 `requestPurchaseItem()`를 이용해
 * 결제를 시도 한다.
 *
 * 4. 결제한 상품을 확인 처리 한다.
 */
interface GoogleBillingHelper {

    /**
     * 인앱 결제 결과 리스너 인스턴스를 반환
     */
    fun getPurchaseResultListener(): Observable<PurchaseState>

    /**
     * 결제 클라이언트 사용가능 여부를 얻는다.
     */
    fun isBillingClientReady(): Boolean

    /**
     * 테스트 아이템의 상품 정보를 가져 온다
     */
    fun queryTestItemSkuDetails()

    /**
     * 인앱 상품을 구매 시도 한다
     *
     * @param skuDetails 수매 시도할 상품의 정보
     */
    fun requestPurchaseItem(skuDetails: SkuDetails)

    /**
     * 결제한 구매를 확인 처리 한다
     */
    fun confirmPurchase(purchase: Purchase)
}

/**
 * 인앱 결제 등록된 아이템
 *
 * skuId string을 resource로 빼야 할까??
 */
enum class PurchaseItems(val skuId: String) {
    GEM_100("gem_100")
}

/**
 * 구매 처리 상태
 * (예제로 사용되는 `State`이며 그냥 인터페이스 콜백해도 됨)
 */
sealed class PurchaseState : State

/**
 * 결제 대기 상태
 */
object BillingServiceReadyState : PurchaseState()

/**
 * 테스트용 결제 상품 목록을 받아온 상태
 */
data class BillingTestItemRecivedState(
    val skuDetails: List<SkuDetails>
) : PurchaseState()

/**
 * 결제 완료 및 결과 데이터 클래스
 */
data class PurchaseSuccessState(
    val billingResult: BillingResult,
    val purchase: Purchase
) : PurchaseState()

/**
 * 결제에 대한 확인이 모두 끝난 데이터 클래스
 */
data class PurchaseConfirmedState(
    val billingResult: BillingResult
) : PurchaseState()

/**
 * 인앱 결제 중 발생한 예외
 */
class PurchaseException(
    message: String
) : RuntimeException(message)