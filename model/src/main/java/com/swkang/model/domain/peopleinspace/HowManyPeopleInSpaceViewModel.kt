package com.swkang.model.domain.peopleinspace

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.swkang.common.exts.rx.subscribeAndDisposed
import com.swkang.model.base.BaseStateViewModel
import com.swkang.model.base.helper.AlertDialogButton
import com.swkang.model.base.helper.MessageHelper
import com.swkang.model.domain.peopleinspace.datas.SpacePeople
import com.swkang.model.domain.peopleinspace.repository.HowManyPeopleInSpaceRepository

class HowManyPeopleInSpaceViewModel @ViewModelInject constructor(
    private val repository: HowManyPeopleInSpaceRepository,
    private val messageHelper: MessageHelper
) : BaseStateViewModel<PeopleInSpaceState>() {

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _peopleCounts = MutableLiveData(0)
    val peopleCounts: LiveData<Int>
        get() = _peopleCounts

    private val _peoples = MutableLiveData<List<SpacePeople>>(emptyList())
    val peoples: LiveData<List<SpacePeople>>
        get() = _peoples

    init {
        repository.stateSubscribe()
            .subscribeAndDisposed(
                this,
                { render(it) }
            )

        dispatchRetrievePeoplesInSpaceAction()
    }

    override fun render(state: PeopleInSpaceState) {
        when (state) {
            is InitializedState -> {
                _isLoading.value = false
                _peopleCounts.value = 0
                _peoples.value = emptyList()
            }
            is PeoplesInSpaceReceiveSuccessState -> {
                _isLoading.value = false
                _peopleCounts.value = state.peopleCounts
                _peoples.value = state.peoples
            }
            is PeoplesInSpaceReceiveErrorState -> {
                _isLoading.value = false
                messageHelper.createSimpleRetryDialog()
                    .subscribeAndDisposed(
                        this,
                        {
                            if (it == AlertDialogButton.POSITIVE) {
                                dispatchRetrievePeoplesInSpaceAction()
                            } else {
                                repository.dispatch(InitializeAction)
                            }
                        }
                    )
            }
        }
    }

    private fun dispatchRetrievePeoplesInSpaceAction() {
        _isLoading.value = true
        repository.dispatch(RetrievePeoplesInSpaceAction)
    }

}