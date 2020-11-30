package com.swkang.playground.repository.covid19

import com.swkang.model.domain.covid19.datas.covid19api.Covid19ApiDatas
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface Covid19WorldApi {

    @GET("summary")
    fun requestWorldStatusSummary(): Single<Covid19ApiDatas>

}