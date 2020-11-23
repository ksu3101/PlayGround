package com.swkang.model.domain.covid19.repository

import com.swkang.model.domain.covid19.datas.krcorona19.Corona19KrCounter
import com.swkang.model.domain.covid19.datas.krcorona19.Corona19KrCountryStatus
import io.reactivex.rxjava3.core.Single

interface Covid19Repository {

    /**
     * 국내 코로나 정보 카운터 요청
     *
     * - 제공 정보: 전일대비 확진환자 증감, 확진환자수, 격리해제수, 사망자수, 발생률 등
     * - 정보 출처: http://ncov.mohw.go.kr/
    */
    fun requestKoreaCounter(): Single<Corona19KrCounter>

    /**
     * 국내 코로나 시도별 발생 동향 요청
     *
     * - 제공 정보: 전일대비 확진환자 증감, 확진환자수, 격리해제수, 사망자수, 발생률 등
     * - 정보 출처: http://ncov.mohw.go.kr/
     */
    fun requestCountryStatus(): Single<Corona19KrCountryStatus>

    /**
     * 매일 업데이트 되는 국가별 신규등 모든 케이스의 요약본 요청
     */
    fun requestWorldStatusSummary()


}