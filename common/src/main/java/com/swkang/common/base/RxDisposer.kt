package com.swkang.common.base

import io.reactivex.rxjava3.disposables.Disposable

interface RxDisposer {
    fun addDisposer(disposable: Disposable)

    fun dispose()
}