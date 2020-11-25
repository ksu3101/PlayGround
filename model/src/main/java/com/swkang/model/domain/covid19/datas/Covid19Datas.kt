package com.swkang.model.domain.covid19.datas

import com.swkang.common.exts.isNumber
import com.swkang.model.domain.covid19.datas.krcorona19.Corona19KrCountry

data class Covid19Infos(
    /** 총 확진자 수 */
    val totalConfirmed: Long,
    /** 새 확진자 수 */
    val newConfirmed: Long,
    /** 총 사망자 수 */
    val totalDeath: Long,
    /** 추가 사망자 수 */
    val newDeath: Long,
    /** 총 완치자 수 */
    val totalRecovered: Long,
    /** 추가 사망자 수 */
    val newRecovered: Long,

    /**
     * 국내 : 각 시도의 정보
     * - 18개 : (서울, 부산, 대구, 인천, 광주, 대전, 울산, 세종, 경기, 강원, 충북, 충남, 전북, 전남, 경북, 경남, 제주, 검역)
     * 세계 : 주요 국가의 정보
     * - 18국가 확진자수 순
     */
    val locations: List<LocationCovid19Infos>
)

data class LocationCovid19Infos(
    /** 지역 이름 */
    val name: String,
    /** 총 확진자 수 */
    val totalConfirmed: Long,
    /** 새 확진자 수 */
    val newConfirmed: Long,
    /** 총 사망자 수 */
    val totalDeath: Long,
    /** 추가 사망자 수 */
    val newDeath: Long? = null,
    /** 총 완치자 수 */
    val totalRecovered: Long? = null,
    /** 추가 사망자 수 */
    val newRecovered: Long? = null,
)

fun Corona19KrCountry.toLocationCovid19Infos(): LocationCovid19Infos {
    return LocationCovid19Infos(
        this.countryName,
        this.totalCase.toNumberOnly(),
        this.newCase.toNumberOnly(),
        this.death.toNumberOnly()
    )
}

fun String?.toNumberOnly(): Long {
    if (this.isNullOrEmpty()) return 0
    else if (this.contains(",")) {
        return this.replace(",", "").toLong()
    } else if (this.isNumber()) {
        return this.toLong()
    }
    throw IllegalArgumentException("`$this` is not Number.")
}

/*
  "Global": {
    "NewConfirmed": 100282,
    "TotalConfirmed": 1162857,
    "NewDeaths": 5658,
    "TotalDeaths": 63263,
    "NewRecovered": 15405,
    "TotalRecovered": 230845
  },
  [
    {
      "Country": "Korea (South)",
      "CountryCode": "KR",
      "Slug": "korea-south",
      "NewConfirmed": 94,
      "TotalConfirmed": 10156,
      "NewDeaths": 3,
      "TotalDeaths": 177,
      "NewRecovered": 304,
      "TotalRecovered": 6325,
      "Date": "2020-04-05T06:37:00Z"
    },
 */