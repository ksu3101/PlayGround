package com.swkang.model.base

import com.swkang.common.exts.rx.subscribeAndDispose

abstract class BaseStateViewModel<A : Action, S : State>(
    stateRepository: BaseStateRepository<A, S>
) : BaseViewModel() {

    init {
        stateRepository.stateSubscribe()
            .subscribeAndDispose(
                this,
                { render(it) }
            )
    }

    abstract fun render(state: S)

}