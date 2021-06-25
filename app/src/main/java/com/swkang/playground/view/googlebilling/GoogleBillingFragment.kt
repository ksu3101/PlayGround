package com.swkang.playground.view.googlebilling

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import com.swkang.model.base.BaseViewModel
import com.swkang.model.base.helper.BillingServiceReadyState
import com.swkang.model.base.helper.GoogleBillingHelper
import com.swkang.model.domain.googlebilling.GoogleBillingViewModel
import com.swkang.playground.R
import com.swkang.playground.base.BaseFragment
import com.swkang.playground.base.ui.OnClickListener
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

@AndroidEntryPoint
class GoogleBillingFragment : BaseFragment(R.layout.googlebilling_fragment), OnClickListener {
    private val compositeDisposal: CompositeDisposable by lazy { CompositeDisposable() }
    private val vm: GoogleBillingViewModel by viewModels()

    @Inject
    lateinit var billingHelper: GoogleBillingHelper

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
                    Log.d(
                        "GoogleBillingFragment",
                        ">> purchase result listener = ${it.javaClass.name}"
                    )
                    //vm.purchaseResultHandler(it)

                    when (it) {
                        BillingServiceReadyState -> {
                            // TODO : 결제 초기화 완료 및 구매 대기 상태 -> 결제 버튼 활성화 시켜준다.
                            purchaseButton?.isEnabled = true
                        }
                        // ...
                    }

                }, {
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
                billingHelper.queryTestItemSkuDetails()
            }
        }
    }

    override fun onDestroy() {
        compositeDisposal.clear()
        super.onDestroy()
    }
}