package com.swkang.model.domain.covid19

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.swkang.common.DEFAULT_TIMESTAMP
import com.swkang.common.exts.addFirst
import com.swkang.common.exts.rx.subscribeAndDisposed
import com.swkang.model.R
import com.swkang.model.base.BaseViewModel
import com.swkang.model.base.helper.MessageHelper
import com.swkang.model.base.helper.ResourceHelper
import com.swkang.model.domain.covid19.datas.Covid19Datas
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
    private val _totalCase = MutableLiveData<Long>(0)
    val totalCase: LiveData<Long>
        get() = _totalCase

    // 추가 확진자 수
    private val _todayNewCase = MutableLiveData<Long>(0)
    val todayNewCase: LiveData<Long>
        get() = _todayNewCase

    // 국내 총 사망자 수
    private val _totalDeath = MutableLiveData<Long>(0)
    val totalDeath: LiveData<Long>
        get() = _totalDeath

    // 추가 사망자 수
    private val _todayNewDeath = MutableLiveData<Long>(0)
    val todayNewDeath: LiveData<Long>
        get() = _todayNewDeath

    // 국내 총 완치자 수
    private val _totalRecovered = MutableLiveData<Long>(0)
    val totalRecovered: LiveData<Long>
        get() = _totalRecovered

    // 추가 완치자 수
    private val _todayNewRecovered = MutableLiveData<Long>(0)
    val todayNewRecovered: LiveData<Long>
        get() = _todayNewRecovered

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
        _totalCase.value = 0
        _todayNewCase.value = 0

        _totalDeath.value = 0
        _todayNewDeath.value = 0

        _totalRecovered.value = 0
        _todayNewRecovered.value = 0

        _covid19Countries.value = emptyList()
    }

    private fun updateViews(covid19Infos: Covid19Infos) {
        // api call 시간
        _currentTimeStamp.value = getTimeStamp()

        // 총 확진자
        _totalCase.value = covid19Infos.totalConfirmed
        _todayNewCase.value = covid19Infos.newConfirmed

        // 총 사망자
        _totalDeath.value = covid19Infos.totalDeath
        _todayNewDeath.value = covid19Infos.newDeath

        // 총 완치자
        _totalRecovered.value = covid19Infos.totalRecovered
        _todayNewRecovered.value = covid19Infos.newRecovered

        // 지역별 covid19 정보 리스트
        // 맨 앞에 TOP header를 추가 한다. (TODO : header에 대한 apdater 재정의 필요)
        _covid19Countries.value = covid19Infos.locations.addFirst(
            Covid19Datas.DUMMY_LOC_COVID19INFOS
        )
    }

    private fun getTimeStamp(): String {
        val sdf = SimpleDateFormat(DEFAULT_TIMESTAMP, Locale.KOREA)
        sdf.timeZone = TimeZone.getTimeZone("Asia/Seoul")
        return sdf.format(Calendar.getInstance().time)
    }

}