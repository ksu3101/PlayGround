package com.swkang.playground.repository.covid19

import com.swkang.model.domain.covid19.datas.krcorona19.Corona19KrCounter
import com.swkang.model.domain.covid19.datas.krcorona19.Corona19KrCountryStatus
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface Covid19KrApi {

    @GET("korea/")
    fun requestKoreaCounter(
        @Query("serviceKey") serviceKey: String
    ): Single<Corona19KrCounter>

    @GET("korea/country/new/")
    fun requestCountryStatus(
        @Query("serviceKey") serviceKey: String
    ): Single<Corona19KrCountryStatus>

}