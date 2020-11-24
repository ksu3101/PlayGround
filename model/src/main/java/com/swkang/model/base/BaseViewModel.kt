package com.swkang.model.base

import androidx.lifecycle.ViewModel
import com.swkang.common.base.RxDisposer
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

abstract class BaseViewModel : ViewModel(), RxDisposer {
    private lateinit var compositeDisposable: CompositeDisposable

    override fun addDisposer(disposable: Disposable) {
        if (!::compositeDisposable.isInitialized) {
            compositeDisposable = CompositeDisposable()
        }
        compositeDisposable.add(disposable)
    }

    override fun dispose() {
        if (::compositeDisposable.isInitialized) {
            compositeDisposable.dispose()
        }
    }

    override fun onCleared() {
        dispose()
    }
}