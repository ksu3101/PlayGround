package com.swkang.model.domain.peopleinspace

import com.swkang.model.base.Action
import com.swkang.model.base.BaseStateRepository
import java.lang.IllegalArgumentException

class HowManyPeopleInSpaceRepository(
    // TODO : api 인스턴스 필요
): BaseStateRepository<HowManyPeopleInSpaceState>() {

    override fun dispatcher(action: Action) {
        actionHandler(action)
    }

    override fun actionHandler(action: Action): HowManyPeopleInSpaceState {
        return when (action) {
            is InitializeAction -> InitializedState
            is RetrievePeoplesInSpace -> {

                // TODO : api 찌르고 분기 처리
                PeoplesInSpaceRetrieveCompleteState(emptyList())

            }
            else -> throw IllegalArgumentException("unknown `$action` has dispatched.")
        }
    }
}