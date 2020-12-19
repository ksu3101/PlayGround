package com.swkang.model.domain.peopleinspace

import androidx.hilt.lifecycle.ViewModelInject
import com.swkang.common.exts.rx.subscribeAndDisposed
import com.swkang.model.base.BaseStateViewModel
import com.swkang.model.base.helper.MessageHelper
import com.swkang.model.base.helper.ResourceHelper

class HowManyPeopleInSpaceViewModel @ViewModelInject constructor(
    repository: HowManyPeopleInSpaceRepository,
    private val messageHelper: MessageHelper,
    private val resourceHelper: ResourceHelper
) : BaseStateViewModel<HowManyPeopleInSpaceState>() {

    init {
        repository.stateSubscriber()
            .subscribeAndDisposed(
                this,
                { render(it) }
            )
    }

    override fun render(state: HowManyPeopleInSpaceState) {
        when (state) {
            is InitializedState -> {

            }
            is PeoplesInSpaceRetrieveCompleteState -> {

            }
            is PeoplesInSpaceRetrieveErrorUseCase -> {
                messageHelper.showToast(state.errorMessage)
            }
        }
    }

}