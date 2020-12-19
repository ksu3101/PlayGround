package com.swkang.playground.repository.peopleinspace

import com.swkang.model.domain.peopleinspace.datas.SpacePeopleData
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface HowManyPeopleInSpaceApi {

    @GET("astros.json")
    fun requestPeoplesInSpace(): Single<SpacePeopleData>

}