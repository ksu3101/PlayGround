package com.swkang.model.domain.peopleinspace

import com.swkang.model.base.Action
import com.swkang.model.base.State
import com.swkang.model.domain.peopleinspace.datas.SpacePeople

// - - ACTION - - - - - - - - - - - - - - - - - -

sealed class PeopleInSpaceAction : Action

object InitializeAction : PeopleInSpaceAction()

object RetrievePeoplesInSpaceAction : PeopleInSpaceAction()

// - - STATE - - - - - - - - - - - - - - - - - - -

sealed class PeopleInSpaceState : State

object InitializedState : PeopleInSpaceState()

data class PeoplesInSpaceReceiveSuccessState(
    val peopleCounts: Int,
    val peoples: List<SpacePeople>
) : PeopleInSpaceState()

data class PeoplesInSpaceReceiveErrorState(
    val errorMessage: String
) : PeopleInSpaceState()