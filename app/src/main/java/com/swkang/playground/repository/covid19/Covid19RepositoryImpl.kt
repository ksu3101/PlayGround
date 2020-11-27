package com.swkang.playground.repository.covid19

import com.swkang.model.domain.covid19.datas.Covid19Infos
import com.swkang.model.domain.covid19.datas.covid19api.toCovid19Infos
import com.swkang.model.domain.covid19.datas.krcorona19.Corona19KrCounter
import com.swkang.model.domain.covid19.datas.krcorona19.Corona19KrCountryStatus
import com.swkang.model.domain.covid19.datas.krcorona19.toCovid19InfosFromKrDatas
import com.swkang.model.domain.covid19.repository.Covid19Repository
import com.swkang.playground.BuildConfig
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class Covid19RepositoryImpl(
    private val krApi: Covid19KrApi,
    private val worldApi: Covid19WorldApi
) : Covid19Repository {

    override fun requestKoreaStatus(): Single<Covid19Infos> {
        return Single.zip(
            krApi.requestKoreaCounter(BuildConfig.CORONA10KR_API_KEY),
            krApi.requestCountryStatus(BuildConfig.CORONA10KR_API_KEY)
        ) { krCounter: Corona19KrCounter, krCountyStatus: Corona19KrCountryStatus ->
            toCovid19InfosFromKrDatas(krCounter, krCountyStatus)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun requestWorldStatusSummary(): Single<Covid19Infos> {
        return worldApi.requestWorldStatusSummary()
            .map { toCovid19Infos(it) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}