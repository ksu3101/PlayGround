package com.swkang.model.domain.covid19.datas.krcorona19

import com.squareup.moshi.Json

data class Corona19KrCounter(
    val resultCode: Int,
    val resultMessage: String,
    @field:Json(name = "TotalCase") val totalCase: String,
    @field:Json(name = "TotalRecovered") val totalRecovered: String,
    @field:Json(name = "TotalDeath") val totalDeath: String,
    @field:Json(name = "NotCase") val nowCase: String,
    val city1n: String,
    val city2n: String,
    val city3n: String,
    val city4n: String,
    val city5n: String,
    val city1p: String,
    val city2p: String,
    val city3p: String,
    val city4p: String,
    val city5p: String,
    val recoveredPercentage: Double,
    val deathPercentage: Double,
    val checkingCounter: String,
    val checkingPercentage: String,
    val caseCount: String,
    val casePercentage: String,
    @field:Json(name = "notcaseCount") val notCaseCount: String,
    @field:Json(name = "TotalChecking") val totalChecking: String,
    @field:Json(name = "TodayRecovered") val todayRecovered: String,
    @field:Json(name = "TodayDeath") val todayDeath: String,
    @field:Json(name = "TotalCaseBefore") val totalCaseBefore: String,
    val updateTime: String
)

data class Corona19KrCountryStatus(
    val resultCode: Int,
    val resultMessage: String,
    val korea: Corona19KrCountry,
    val seoul: Corona19KrCountry,
    val busan: Corona19KrCountry,
    val daegu: Corona19KrCountry,
    val incheon: Corona19KrCountry,
    val gwangju: Corona19KrCountry,
    val daejeon: Corona19KrCountry,
    val ulsan: Corona19KrCountry,
    val sejong: Corona19KrCountry,
    val gyeonggi: Corona19KrCountry,
    val gangwon: Corona19KrCountry,
    val chungbuk: Corona19KrCountry,
    val chungnam: Corona19KrCountry,
    val jeonbuk: Corona19KrCountry,
    val jeonnam: Corona19KrCountry,
    val gyeongbuk: Corona19KrCountry,
    val gyeongnam: Corona19KrCountry,
    val jeju: Corona19KrCountry,
    val quarantine: Corona19KrCountry
)

data class Corona19KrCountry(
    val countryName: String,
    val newCase: String,
    val totalCase: String,
    val recovered: String,
    val death: String,
    val percentage: String,
    val newFcase: String,
    val newCcase: String
)