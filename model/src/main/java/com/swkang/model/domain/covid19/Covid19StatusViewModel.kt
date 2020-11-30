package com.swkang.model.domain.covid19

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.swkang.common.DEFAULT_TIMESTAMP
import com.swkang.common.exts.rx.subscribeAndDisposed
import com.swkang.common.exts.toCommaNumberString
import com.swkang.model.R
import com.swkang.model.base.BaseViewModel
import com.swkang.model.base.helper.MessageHelper
import com.swkang.model.base.helper.ResourceHelper
import com.swkang.model.domain.covid19.datas.Covid19Infos
import com.swkang.model.domain.covid19.datas.LocationCovid19Infos
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

    // 추가 확진자 수
    private val _todayNewCase = MutableLiveData("")
    val todayNewCase: LiveData<String>
        get() = _todayNewCase

    private val _isPositiveTodayNewCase = MutableLiveData(false)
    val isPositiveTodayNewCase: LiveData<Boolean>
        get() = _isPositiveTodayNewCase

    // 국내 총 완치자 수
    private val _totalRecovered = MutableLiveData("")
    val totalRecovered: LiveData<String>
        get() = _totalRecovered

    // 국내 총 사망자 수
    private val _totalDeath = MutableLiveData("")
    val totalDeath: LiveData<String>
        get() = _totalDeath

    // 추가 사망자 수
    private val _todayNewDeath = MutableLiveData("")
    val todayNewDeath: LiveData<String>
        get() = _todayNewDeath

    private val _isPositiveTodayNewDeath = MutableLiveData(false)
    val isPositiveTodayNewDeath: LiveData<Boolean>
        get() = _isPositiveTodayNewDeath

    // 국내 : 각 시도별 확진자 정보
    // 세계 : 확진자 순 정렬된 각 국가의 확진자 정보
    // 국내, 세계 모두 최대 18개 까지 출력
    private val _covid19Countries = MutableLiveData<List<LocationCovid19Infos>>(emptyList())
    val covid19Countries: LiveData<List<LocationCovid19Infos>>
        get() = _covid19Countries

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
        _currentTimeStamp.value = ""
        _totalCase.value = ""
        _todayNewCase.value = ""

        _covid19Countries.value = emptyList()
        // TODO : 추가 뷰 작업

    }

    private fun updateViews(covid19Infos: Covid19Infos) {
        // api call 시간
        _currentTimeStamp.value = getTimeStamp()

        // 총 확진자
        _totalCase.value = covid19Infos.totalConfirmed.toCommaNumberString()
        // 오늘 추가 확진자
        val todayNewCaseOp =
            if (covid19Infos.newConfirmed < 0) "- " else if (covid19Infos.newConfirmed > 0) "+ " else ""
        _todayNewCase.value = todayNewCaseOp + covid19Infos.newConfirmed.toCommaNumberString()
        _isPositiveTodayNewCase.value = covid19Infos.newConfirmed <= 0

        // 총 사망자
        _totalDeath.value = covid19Infos.totalDeath.toCommaNumberString()
        // 오늘 추가 사망자
        val todayNewDeathOp =
            if (covid19Infos.newDeath < 0) "- " else if (covid19Infos.newDeath > 0) "+ " else ""
        _todayNewDeath.value = todayNewDeathOp + covid19Infos.newDeath.toCommaNumberString()
        _isPositiveTodayNewDeath.value = covid19Infos.newDeath <= 0

        // 총 완치자

        // TODO

        _covid19Countries.value = covid19Infos.locations

    }

    private fun getTimeStamp(): String {
        val sdf = SimpleDateFormat(DEFAULT_TIMESTAMP, Locale.KOREA)
        sdf.timeZone = TimeZone.getTimeZone("Asia/Seoul")
        return sdf.format(Calendar.getInstance().time)
    }

}