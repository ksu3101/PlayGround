package com.swkang.model.domain.covid19

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.swkang.common.exts.rx.subscribeAndDisposed
import com.swkang.model.R
import com.swkang.model.base.BaseViewModel
import com.swkang.model.base.helper.MessageHelper
import com.swkang.model.base.helper.ResourceHelper
import com.swkang.model.domain.covid19.datas.krcorona19.Corona19KrCounter
import com.swkang.model.domain.covid19.repository.Covid19Repository

class Covid19StatusViewModel @ViewModelInject constructor(
    private val covid19Repo: Covid19Repository,
    private val messageHelper: MessageHelper,
    private val resourceHelper: ResourceHelper
) : BaseViewModel() {

    // 국내 총 확진자 수
    private val _totalCase = MutableLiveData("")
    val totalCase: LiveData<String>
        get() = _totalCase

    // 국내 총 완치자 수
    private val _totalRecovered = MutableLiveData("")
    val totalRecovered: LiveData<String>
        get() = _totalRecovered

    // 국내 총 사망자 수
    private val _totalDeath = MutableLiveData("")
    val totalDeath: LiveData<String>
        get() = _totalDeath


    init {
        requestCovid19StatusOfKorea()
    }

    private fun requestCovid19StatusOfKorea() {
        covid19Repo.requestKoreaCounter()
            .subscribeAndDisposed(
                this,
                {
                    updateViews(it)
                },
                {
                    messageHelper.showToast(
                        if (it.message.isNullOrEmpty()) {
                            resourceHelper.getString(R.string.c_error)
                        } else {
                            it.message!!
                        }
                    )
                }
            )
    }

    private fun updateViews(counter: Corona19KrCounter) {
        _totalCase.value = counter.totalCase

    }

}