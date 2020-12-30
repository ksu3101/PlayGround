package com.swkang.model.domain.peopleinspace.repository

import com.swkang.model.base.BaseStateRepository
import com.swkang.model.domain.peopleinspace.PeopleInSpaceAction
import com.swkang.model.domain.peopleinspace.PeopleInSpaceState

interface HowManyPeopleInSpaceRepository
    : BaseStateRepository<PeopleInSpaceAction, PeopleInSpaceState> {
}