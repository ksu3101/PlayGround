package com.swkang.model.base

import com.swkang.common.exts.rx.subscribeAndDisposed

abstract class BaseStateViewModel<A : Action, S : State>(
    stateRepository: BaseStateRepository<A, S>
) : BaseViewModel() {

    init {
        stateRepository.stateSubscribe()
            .subscribeAndDisposed(
                this,
                { render(it) }
            )
    }

    abstract fun render(state: S)

}