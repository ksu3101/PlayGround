package com.swkang.model.base

import com.swkang.model.base.redux.State

abstract class BaseStateViewModel<in S : State> : BaseViewModel() {

    abstract fun render(state: S): Boolean

}