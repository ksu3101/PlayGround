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
import java.text.SimpleDateFormat
import java.util.*

class Covid19StatusViewModel @ViewModelInject constructor(
    private val covid19Repo: Covid19Repository,
    private val messageHelper: MessageHelper,
    private val resourceHelper: ResourceHelper
) : BaseViewModel() {
    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    // 한국 | 세계 구분
    private val _isKorean = MutableLiveData(true)
    val isKorean: LiveData<Boolean>
        get() = _isKorean

    // api 완료 시점 - 시간 스탬프
    private val _currentTimeStamp = MutableLiveData("")
    val currentTimeStamp: LiveData<String>
        get() = _currentTimeStamp

    // 국내 총 확진자 수
    private val _totalCase = MutableLiveData("")
    val totalCase: LiveData<String>
        get() = _totalCase

    // 오늘-현재 추가 확진자 수
    private val _todayNewCase = MutableLiveData("")
    val todayNewCase: LiveData<String>
        get() = _todayNewCase

    // 국내 총 완치자 수
    private val _totalRecovered = MutableLiveData("")
    val totalRecovered: LiveData<String>
        get() = _totalRecovered

    // 국내 총 사망자 수
    private val _totalDeath = MutableLiveData("")
    val totalDeath: LiveData<String>
        get() = _totalDeath

    val onRefreshListener: () -> Unit = {
        requestCovid19StatusOfKorea()
    }

    init {

    }

    fun setCountryAndStartRequesting(isKorea: Boolean) {
        _isKorean.value = isKorea
        if (isKorea) {
            requestCovid19StatusOfKorea()
        } else {
            requestCovid19StatusOfWorld()
        }
    }

    private fun requestCovid19StatusOfKorea() {
        covid19Repo.requestKoreaCounter()
            .doOnSubscribe {
                _isLoading.postValue(true)
                clearViews()
            }
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
                    _isLoading.postValue(false)
                }
            )
    }

    private fun requestCovid19StatusOfWorld() {

    }

    private fun clearViews() {
        _currentTimeStamp.value = getTimeStamp()
        _totalCase.value = ""
        _todayNewCase.value = ""

        _isLoading.postValue(false)
    }

    private fun updateViews(counter: Corona19KrCounter) {
        _totalCase.value = counter.totalCase
        _todayNewCase.value = counter.caseCount
    }

    private fun getTimeStamp(): String {
        val sdf = SimpleDateFormat("yyyy년 MM월 dd일 HH:mm", Locale.KOREA)
        sdf.timeZone = TimeZone.getTimeZone("Asia/Seoul")
        return sdf.format(Calendar.getInstance().time)
    }

}