package com.swkang.model.domain.main.nav

interface MainNavigationHelper {

    /**
     * 코로나19 상태 화면을 보여 준다.
     */
    fun openCovid19StatusPage()

    /**
     * 코로나19 상태 화면을 종료 한다.
     */
    fun closeCovid19StatusPage()

    /**
     * 우주에 지금 존재하는 사람들의 목록 화면을 보여 준다.
     */
    fun openHowManyPeoplesInSpacePage()

    /**
     * 우주에 지금 존재하는 사람들의 목록 화면을 종료 한다.
     */
    fun closeHowManyPeoplesInSpacepage()

}