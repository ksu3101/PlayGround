package com.swkang.playground.view.googlebilling

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import com.swkang.model.base.BaseViewModel
import com.swkang.model.base.helper.GoogleBillingHelper
import com.swkang.model.domain.googlebilling.GoogleBillingViewModel
import com.swkang.playground.R
import com.swkang.playground.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

@AndroidEntryPoint
class GoogleBillingFragment : BaseFragment(R.layout.googlebilling_fragment), View.OnClickListener {
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
        rootView?.findViewById<Button>(R.id.googlebilling_btn_starttest)?.setOnClickListener(this)

        compositeDisposal.add(billingHelper.getPurchaseResultListener().subscribe(
            {
                vm.onGoogleBillingResultReceived(it)
            }, {
                vm.onGoogleBillingErrorReceived(it.message ?: "Billing error message not found.")
            }
        ))

        return rootView
    }

    override fun onClick(view: View?) {
        if (view == null) return
        when (view.id) {
            R.id.googlebilling_btn_starttest -> {
                billingHelper
            }
        }
    }

    override fun onDestroy() {
        compositeDisposal.clear()
        super.onDestroy()
    }
}