package com.swkang.model.domain.peopleinspace

import com.swkang.model.base.Action
import com.swkang.model.base.State
import com.swkang.model.domain.peopleinspace.datas.SpacePeople

sealed class HowManyPeopleInSpaceAction : Action

object InitializeAction : HowManyPeopleInSpaceAction()

object RetrievePeoplesInSpace : HowManyPeopleInSpaceAction()


sealed class HowManyPeopleInSpaceState : State

object InitializedState : HowManyPeopleInSpaceState()

data class PeoplesInSpaceRetrieveCompleteState(
    val list: List<SpacePeople>
) : HowManyPeopleInSpaceState()

data class PeoplesInSpaceRetrieveErrorUseCase(
    val errorMessage: String
) : HowManyPeopleInSpaceState()