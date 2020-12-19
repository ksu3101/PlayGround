package com.swkang.model.base

abstract class BaseStateViewModel<in S: State>: BaseViewModel() {

    abstract fun render(state: S)

}