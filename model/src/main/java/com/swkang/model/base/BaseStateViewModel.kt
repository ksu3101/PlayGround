package com.swkang.model.base

import com.swkang.common.exts.rx.subscribeAndDisposed

abstract class BaseStateViewModel<S : State>(
    repository: BaseStateRepository<S>
) : BaseViewModel() {

    init {
        repository.stateSubscriber()
            .subscribeAndDisposed(
                this,
                { render(it) }
            )
    }

    abstract fun render(state: S)

}