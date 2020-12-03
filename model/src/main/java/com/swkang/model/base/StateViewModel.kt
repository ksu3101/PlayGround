package com.swkang.model.base

import com.swkang.model.base.redux.State

abstract class StateViewModel<S : State> : BaseViewModel() {

    abstract fun render(state: S): Boolean

}