package com.swkang.model.domain.covid19.datas.covid19api

import com.squareup.moshi.Json
import com.swkang.common.exts.limit
import com.swkang.model.domain.covid19.datas.Covid19Infos
import com.swkang.model.domain.covid19.datas.LocationCovid19Infos

data class Covid19ApiDatas(
    @field:Json(name = "Global") val globalInfos: GlobalCovid19Status,
    @field:Json(name = "Countries") val countries: List<CountryCovid19Status>
)

data class GlobalCovid19Status(
    @field:Json(name = "NewConfirmed") val newConfirmed: Long,
    @field:Json(name = "TotalConfirmed") val totalConfiremd: Long,
    @field:Json(name = "NewDeaths") val newDeaths: Long,
    @field:Json(name = "TotalDeath") val totalDeaths: Long,
    @field:Json(name = "NewRecovered") val newRecovered: Long,
    @field:Json(name = "TotalRecovered") val totalRecovered: Long
)

data class CountryCovid19Status(
    @field:Json(name = "Country") val countryName: String,
    @field:Json(name = "CountryCode") val countryCode: String,
    @field:Json(name = "Slug") val slug: String,
    @field:Json(name = "Date") val date: String,
    @field:Json(name = "NewConfirmed") val newConfirmed: Long,
    @field:Json(name = "TotalConfirmed") val totalConfiremd: Long,
    @field:Json(name = "NewDeaths") val newDeaths: Long,
    @field:Json(name = "TotalDeath") val totalDeaths: Long,
    @field:Json(name = "NewRecovered") val newRecovered: Long,
    @field:Json(name = "TotalRecovered") val totalRecovered: Long
)

fun toCovid19Infos(worldDatas: Covid19ApiDatas): Covid19Infos {
    val currentTime = System.currentTimeMillis()
    return Covid19Infos(
        currentTime,
        worldDatas.globalInfos.totalConfiremd,
        worldDatas.globalInfos.newConfirmed,
        worldDatas.globalInfos.totalDeaths,
        worldDatas.globalInfos.newDeaths,
        worldDatas.globalInfos.totalRecovered,
        worldDatas.globalInfos.newRecovered,
        worldDatas.countries.sortedWith(compareBy { it.totalConfiremd })
            .limit(18)
            .map {
                LocationCovid19Infos(
                    currentTime,
                    it.countryName,
                    it.totalConfiremd,
                    it.newConfirmed,
                    it.totalDeaths,
                    it.newDeaths,
                    it.totalRecovered,
                    it.newRecovered
                )
            }
    )
}