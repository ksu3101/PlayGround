package com.swkang.model.domain.covid19

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.swkang.common.exts.rx.subscribeAndDisposed
import com.swkang.common.exts.toCommaNumberString
import com.swkang.model.R
import com.swkang.model.base.BaseViewModel
import com.swkang.model.base.helper.MessageHelper
import com.swkang.model.base.helper.ResourceHelper
import com.swkang.model.domain.covid19.datas.Covid19Infos
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

    // 새로고침 이벤트 핸들러
    val onRefreshListener: () -> Unit = {
        setCountryAndStartRequesting(isKorean.value!!)
    }

    /**
     * 외부에서 한국, 세계 여부를 설정 하고 그에 따라 한국, 세계의 코로나19
     * 현황을 가져온다.
     *
     * @param isKorea 한국 | 세계 인지 여부
     */
    fun setCountryAndStartRequesting(isKorea: Boolean) {
        _isKorean.value = isKorea
        requestCovid19Status(isKorea)
    }

    private fun requestCovid19Status(isKorea: Boolean) {
        if (isKorea) {
            covid19Repo.requestKoreaStatus()
        } else {
            covid19Repo.requestWorldStatusSummary()
        }.doOnSubscribe {
            _isLoading.postValue(true)
            clearViews()
        }.doFinally {
            _isLoading.postValue(false)
        }.subscribeAndDisposed(
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

    private fun clearViews() {
        _currentTimeStamp.value = getTimeStamp()
        _totalCase.value = ""
        _todayNewCase.value = ""

        // TODO : 추가 뷰 작업

    }

    private fun updateViews(counter: Covid19Infos) {
        _totalCase.value = counter.totalConfirmed.toCommaNumberString()
        _todayNewCase.value = counter.newConfirmed.toCommaNumberString()

        // TODO : 추가 뷰 작업

    }

    private fun getTimeStamp(): String {
        val sdf = SimpleDateFormat("yyyy년 MM월 dd일 HH:mm", Locale.KOREA)
        sdf.timeZone = TimeZone.getTimeZone("Asia/Seoul")
        return sdf.format(Calendar.getInstance().time)
    }

}