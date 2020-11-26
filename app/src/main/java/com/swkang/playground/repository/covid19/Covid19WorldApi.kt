package com.swkang.playground.repository.covid19

import com.swkang.model.domain.covid19.datas.covid19api.Covid19ApiDatas
import io.reactivex.rxjava3.core.Single

interface Covid19WorldApi {

    fun requestWorldStatusSummary(): Single<Covid19ApiDatas>

}