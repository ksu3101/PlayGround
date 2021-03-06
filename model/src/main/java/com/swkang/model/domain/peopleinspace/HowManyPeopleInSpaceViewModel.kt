package com.swkang.model.domain.peopleinspace

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.swkang.common.exts.rx.subscribeAndDispose
import com.swkang.model.base.BaseStateViewModel
import com.swkang.model.base.helper.MessageHelper
import com.swkang.model.domain.peopleinspace.datas.SpacePeople
import com.swkang.model.domain.peopleinspace.repository.HowManyPeopleInSpaceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HowManyPeopleInSpaceViewModel @Inject constructor(
    private val repository: HowManyPeopleInSpaceRepository,
    private val messageHelper: MessageHelper
) : BaseStateViewModel<PeopleInSpaceAction, PeopleInSpaceState>(repository) {

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
                    .subscribeAndDispose(
                        this,
                        {
                            if (it.isPositiveButton()) {
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