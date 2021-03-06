package com.swkang.model.domain.covid19.datas.krcorona19

import com.squareup.moshi.Json
import com.swkang.common.exts.isSignedNumber
import com.swkang.model.domain.covid19.datas.Covid19Infos
import com.swkang.model.domain.covid19.datas.LocationCovid19Infos

data class Corona19KrCounter(
    /** 응답 코드 : 0, 401(Wrong key) */
    val resultCode: Int,
    /** 정상처리: (정상 처리되었습니다.) / 오류(권한이 없거나 잘못된 키 입니다. "github.com/dhlife09/Corona-19-API"에 방문하세요.) */
    val resultMessage: String,

    /** 국내 확진자 수 */
    @field:Json(name = "TotalCase") val totalCase: String,
    /** 국내 완치자 수 */
    @field:Json(name = "TotalRecovered") val totalRecovered: String,
    /** 국내 사망자 수 */
    @field:Json(name = "TotalDeath") val totalDeath: String,
    /** 국내 격리자 수 */
    @field:Json(name = "NowCase") val nowCase: String,

    /** 시도별 확진 환자 현황1 - 시도 이름 */
    val city1n: String,
    val city2n: String,
    val city3n: String,
    val city4n: String,
    val city5n: String,
    /** 시도별 확진 환자 현황1 - 확진환자 퍼센트 율 */
    val city1p: String,
    val city2p: String,
    val city3p: String,
    val city4p: String,
    val city5p: String,

    /** 국내 완치율 (퍼센트) */
    val recoveredPercentage: String,
    /** 국내 사망율 (퍼센트) */
    val deathPercentage: String,
    /** 국내 검사중 (명) */
    val checkingCounter: String,
    /** 국내 검사중 (퍼센트) */
    val checkingPercentage: String,

    /** 국내 검사결과 양성 (명) */
    val caseCount: String,
    /** 국내 검사결과 양성 (퍼센트) */
    val casePercentage: String,

    /** 국내 검사결과 음성 (명) */
    @field:Json(name = "notcaseCount") val notCaseCount: String,
    /** 총 검사 완료 (명)  */
    @field:Json(name = "TotalChecking") val totalChecking: String,
    /** 오늘 하루 완치자 수 (명) */
    @field:Json(name = "TodayRecovered") val todayRecovered: String,
    /** 오늘 하루 사망자 수 (명) */
    @field:Json(name = "TodayDeath") val todayDeath: String,
    /** 전달 대비 환자 수 (명) */
    @field:Json(name = "TotalCaseBefore") val totalCaseBefore: String,
    /** 정보 업데이트 기준 : 00시정보로 오전10시경 자동 업데이트 됩니다.
     *  ex) 코로나바이러스감염증-19 국내 발생현황 (4.5. 00시 기준) */
    val updateTime: String
)

data class Corona19KrCountryStatus(
    /** 응답 코드 : 0, 401(Wrong key) */
    val resultCode: Int,
    val resultMessage: String,
    //val korea: Corona19KrCountry,
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
    // 지역명
    val countryName: String,
    // 신규 확진자 수
    val newCase: String,
    // 총 확진 환자 수
    val totalCase: String,
    // 완치자 수
    val recovered: String,
    // 사망자 수
    val death: String,
    // 발생률
    val percentage: String,
    // 전일 대비 증감 - 해외지역
    val newFcase: String,
    // 전일 대비 증감 - 지역발생
    val newCcase: String
)

fun toCovid19InfosFromKrDatas(
    krCounter: Corona19KrCounter,
    country: Corona19KrCountryStatus
): Covid19Infos {
    val currentTime = System.currentTimeMillis()
    return Covid19Infos(
        currentTime,
        krCounter.totalCase.toNumberOnly(),
        krCounter.totalCaseBefore.toNumberOnly(),
        krCounter.totalDeath.toNumberOnly(),
        krCounter.todayDeath.toNumberOnly(),
        krCounter.totalRecovered.toNumberOnly(),
        krCounter.todayRecovered.toNumberOnly(),
        parseLocationsFrom(currentTime, country)
    )
}

private fun parseLocationsFrom(
    currentTime: Long,
    country: Corona19KrCountryStatus
): List<LocationCovid19Infos> {
    val locations = listOf(
        country.seoul.toLocationCovid19Infos(0, currentTime),
        country.busan.toLocationCovid19Infos(1, currentTime),
        country.daegu.toLocationCovid19Infos(2, currentTime),
        country.incheon.toLocationCovid19Infos(3, currentTime),
        country.gwangju.toLocationCovid19Infos(4, currentTime),
        country.daejeon.toLocationCovid19Infos(5, currentTime),
        country.ulsan.toLocationCovid19Infos(6, currentTime),
        country.sejong.toLocationCovid19Infos(7, currentTime),
        country.gyeonggi.toLocationCovid19Infos(8, currentTime),
        country.gangwon.toLocationCovid19Infos(9, currentTime),
        country.chungbuk.toLocationCovid19Infos(10, currentTime),
        country.chungnam.toLocationCovid19Infos(11, currentTime),
        country.jeonbuk.toLocationCovid19Infos(12, currentTime),
        country.jeonnam.toLocationCovid19Infos(13, currentTime),
        country.gyeongbuk.toLocationCovid19Infos(14, currentTime),
        country.gyeongnam.toLocationCovid19Infos(15, currentTime),
        country.jeju.toLocationCovid19Infos(16, currentTime),
        country.quarantine.toLocationCovid19Infos(17, currentTime)
    )
    return locations
}

fun Corona19KrCountry.toLocationCovid19Infos(index: Int, currentTime: Long): LocationCovid19Infos {
    return LocationCovid19Infos(
        index,
        currentTime,
        this.countryName,
        this.totalCase.toNumberOnly(),
        this.newCase.toNumberOnly(),
        this.death.toNumberOnly()
    )
}

private fun String?.toNumberOnly(): Long {
    if (this.isNullOrEmpty()) return 0
    var temp = this
    if (temp.contains(",")) {
        temp = this.replace(",", "")
    }
    if (temp.isSignedNumber()) {
        return temp.toLong()
    }
    throw IllegalArgumentException("`$this` is not Number.")
}
