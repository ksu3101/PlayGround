package com.swkang.common.exts.rx

import com.swkang.common.base.RxDisposer
import io.reactivex.rxjava3.core.*


fun <T> Observable<T>.subscribeAndDispose(
    disposer: RxDisposer,
    onSuccess: (T) -> Unit,
    onError: ((Throwable) -> Unit)? = null,
    onComplete: (() -> Unit)? = null,
) {
    disposer.addDisposer(
        this.subscribe(onSuccess, onError, onComplete)
    )
}

fun <T> Flowable<T>.subscribeAndDispose(
    disposer: RxDisposer,
    onSuccess: (T) -> Unit,
    onError: ((Throwable) -> Unit)? = null,
    onComplete: (() -> Unit)? = null,
) {
    disposer.addDisposer(
        this.subscribe(onSuccess, onError, onComplete)
    )
}

fun <T> Maybe<T>.subscribeAndDispose(
    disposer: RxDisposer,
    onSuccess: (T) -> Unit,
    onError: ((Throwable) -> Unit)? = null,
    onComplete: (() -> Unit)? = null,
) {
    disposer.addDisposer(
        this.subscribe(onSuccess, onError, onComplete)
    )
}

fun <T> Single<T>.subscribeAndDispose(
    disposer: RxDisposer,
    onSuccess: (T) -> Unit,
    onError: ((Throwable) -> Unit)? = null
) {
    disposer.addDisposer(
        this.subscribe(onSuccess, onError)
    )
}

fun Completable.subscribeAndDispose(
    disposer: RxDisposer,
    onError: ((Throwable) -> Unit)? = null,
    onComplete: (() -> Unit)
) {
    disposer.addDisposer(
        this.subscribe(onComplete, onError)
    )
}