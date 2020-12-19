package com.swkang.model.domain.peopleinspace

import com.swkang.model.base.Action
import com.swkang.model.base.State
import com.swkang.model.domain.peopleinspace.datas.SpacePeople

// - - ACTION - - - - - - - - - - - - - - - - - -

sealed class HowManyPeopleInSpaceAction : Action

object InitializeAction : HowManyPeopleInSpaceAction()

object RetrievePeoplesInSpaceAction : HowManyPeopleInSpaceAction()

// - - STATE - - - - - - - - - - - - - - - - - - -

sealed class HowManyPeopleInSpaceState : State

object InitializedState : HowManyPeopleInSpaceState()

data class PeoplesInSpaceRetrieveCompleteState(
    val peopleCounts: Int,
    val peoples: List<SpacePeople>
) : HowManyPeopleInSpaceState()

data class PeoplesInSpaceRetrieveErrorState(
    val errorMessage: String
) : HowManyPeopleInSpaceState()