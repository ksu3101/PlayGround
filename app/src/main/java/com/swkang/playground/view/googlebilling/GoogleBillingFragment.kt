package com.swkang.playground.view.googlebilling

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import com.android.billingclient.api.SkuDetails
import com.swkang.model.base.BaseViewModel
import com.swkang.model.base.helper.BillingServiceReadyState
import com.swkang.model.base.helper.BillingTestItemReceivedState
import com.swkang.model.base.helper.GoogleBillingHelper
import com.swkang.model.base.helper.MessageHelper
import com.swkang.model.base.helper.PurchaseConfirmedState
import com.swkang.model.base.helper.PurchaseSuccessState
import com.swkang.model.domain.googlebilling.GoogleBillingViewModel
import com.swkang.playground.R
import com.swkang.playground.base.BaseFragment
import com.swkang.playground.base.ui.OnClickListener
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

@AndroidEntryPoint
class GoogleBillingFragment : BaseFragment(R.layout.googlebilling_fragment), OnClickListener {
    private val TAG = "GoogleBillingModule //Fragment"
    private val compositeDisposal: CompositeDisposable by lazy { CompositeDisposable() }
    private val vm: GoogleBillingViewModel by viewModels()

    private var lastPurchaseSkuDetails: SkuDetails? = null

    @Inject
    lateinit var billingHelper: GoogleBillingHelper

    @Inject
    lateinit var messageHelper: MessageHelper

    override fun createViewModel(): BaseViewModel = vm

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = super.onCreateView(inflater, container, savedInstanceState)

        val purchaseButton = rootView?.findViewById<Button>(R.id.googlebilling_btn_starttest)
        purchaseButton?.setOnClickListener(this)
        purchaseButton?.isEnabled = false

        compositeDisposal.add(billingHelper.getPurchaseResultListener()
            .doOnSubscribe {
                // start loading progress
            }
            .subscribe(
                {
                    Log.d(TAG, ">> purchase result listener = ${it.javaClass.name}")

                    when (it) {
                        is BillingServiceReadyState -> {
                            // 결제 초기화 완료 및 구매 대기 상태 -> 결제 버튼 활성화
                            purchaseButton?.isEnabled = true
                        }
                        is BillingTestItemReceivedState -> {
                            // 결제 상품 정보 가져온 상태 -> 구매 대기 상태
                            it.skuDetails.mapIndexed { i, skuDetail ->
                                Log.d(TAG, ">> purchase skuDetail[$i] = $skuDetail")
                            }
                            val skuDetail = it.skuDetails[0]
                            lastPurchaseSkuDetails = skuDetail
                            billingHelper.requestPurchaseItem(skuDetail)
                        }
                        is PurchaseSuccessState -> {
                            // 구매 완료 -> 구매 확인 요청
                            Log.d(TAG, ">> requested confirm purchase = ${it.purchase}")
                            billingHelper.confirmPurchase(it.purchase)
                            lastPurchaseSkuDetails = null
                        }
                        is PurchaseConfirmedState -> {
                            // 구매 확인 완료
                            Log.d(
                                TAG,
                                ">> confirmed complete. billingResult =  ${it.billingResult}"
                            )
                            lastPurchaseSkuDetails = null
                            messageHelper.showToast("구매가 완료되었습니다.")  // TODO : move to string resource
                        }
                    }

                }, {
                    Log.e(TAG, "ERROR = ${it.message}")
                    vm.onGoogleBillingErrorReceived(
                        it.message ?: "Billing error message not found."
                    )
                }
                // TODO : 결과혹은 오류 받은 뒤 프로그레스 비 활성화 시켜줌.
            ))

        return rootView
    }

    override fun onResume() {
        super.onResume()

        // 기존에 구매를 진행했지만 구매확인을 하지 않은 건에 대해서 구매 확인을 처리 하도록 해 준다.
        if (billingHelper.isBillingClientReady()) {
            /*
            TODO : 지금은 상품이 하나 뿐이라 아래처럼 한번만 콜 하지만 여러개일 경우에 대한 처리가 필요 하다. 그리고 이 내용이 BaseActivity등에 포함 되어야 할듯?
             */
            billingHelper.queryTestItemSkuDetails()
        }
    }

    override fun onClicked(view: View) {
        when (view.id) {
            R.id.googlebilling_btn_starttest -> {
                if (lastPurchaseSkuDetails != null) {
                    Log.d(TAG, ">> onClicked() // billingHelper.requestPurchaseItem() // lastPurchaseSkuDetails = ${lastPurchaseSkuDetails!!}")
                    billingHelper.requestPurchaseItem(lastPurchaseSkuDetails!!)
                } else {
                    Log.d(TAG, ">> onClicked() // billingHelper.queryTestItemSkuDetails()")
                    billingHelper.queryTestItemSkuDetails()
                }
            }
        }
    }

    override fun onDestroy() {
        compositeDisposal.clear()
        super.onDestroy()
    }
}