package com.swkang.playground.repository.covid19

import com.swkang.model.domain.covid19.datas.covid19api.Covid19ApiDatas
import com.swkang.model.domain.covid19.datas.krcorona19.Corona19KrCounter
import com.swkang.model.domain.covid19.datas.krcorona19.Corona19KrCountryStatus
import com.swkang.model.domain.covid19.repository.Covid19Repository
import com.swkang.playground.BuildConfig
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class Covid19RepositoryImpl(
    private val krApi: Covid19KrApi,
    private val worldApi: Covid19WorldApi
) : Covid19Repository {
    override fun requestKoreaCounter(): Single<Corona19KrCounter> {
        return krApi.requestKoreaCounter(BuildConfig.CORONA10KR_API_KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun requestCountryStatus(): Single<Corona19KrCountryStatus> {
        return krApi.requestCountryStatus(BuildConfig.CORONA10KR_API_KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun requestWorldStatusSummary(): Single<Covid19ApiDatas> {
        return worldApi.requestWorldStatusSummary()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}