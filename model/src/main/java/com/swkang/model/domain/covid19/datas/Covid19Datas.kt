package com.swkang.model.domain.covid19.datas

data class Covid19Infos(
    /** 데이터 생성 시간 */
    val date: Long,
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
    /** 추가 완치자 수 */
    val newRecovered: Long,

    /**
     * 국내 : 각 시도의 정보
     * - 18개 : (서울, 부산, 대구, 인천, 광주, 대전, 울산, 세종, 경기, 강원, 충북, 충남, 전북, 전남, 경북, 경남, 제주, 검역)
     * 세계 : 주요 국가의 정보
     * - 확진자수 순으로 정렬 된 최대 18개의 국가
     */
    val locations: List<LocationCovid19Infos>
)

data class LocationCovid19Infos(
    // 구분하기 위한 인덱스
    val index: Int,
    // 생성 날짜
    val date: Long,
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
    /** 추가 완치자 수 */
    val newRecovered: Long? = null,
)

object Covid19Datas {
    val DUMMY_LOC_COVID19INFOS = LocationCovid19Infos(-1, 0, "", 0, 0, 0, 0)
}
