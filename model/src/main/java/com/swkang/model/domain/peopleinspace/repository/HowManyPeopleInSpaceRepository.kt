package com.swkang.model.domain.peopleinspace.repository

import com.swkang.model.base.BaseStateRepository
import com.swkang.model.domain.peopleinspace.HowManyPeopleInSpaceAction
import com.swkang.model.domain.peopleinspace.HowManyPeopleInSpaceState

interface HowManyPeopleInSpaceRepository
    : BaseStateRepository<HowManyPeopleInSpaceAction, HowManyPeopleInSpaceState> {
}