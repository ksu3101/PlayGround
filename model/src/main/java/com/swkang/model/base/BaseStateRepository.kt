package com.swkang.model.base

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject

/**
 * Use-Case에서 이벤트의 시작점
 */
interface Action

/**
 * Use-Case에서의 이벤트의 결과물 로서 View를 업데이트 하기 위한 유일한 Data 기반 클래스
 */
interface State

/**
 * Use case를 `Action-State`으로 정의 한 뒤 이를 Uni-Direction flow로 처리 하는 `Repository`.
 * ViewModel에서 `dispatcher()`를 통해서 `Action`을 런칭 하면 리포지터리 내부의 `actionHandler()`
 * 에서 비즈니스 로직을 통해서 `State`를 반환하고, 이를 `stateSubscriber()`를 통해 구독했을 경우
 * 발행 될 `Observer<State>`를 받아 View를 갱신 한다.
 */
abstract class BaseStateRepository<in A : Action, S : State> {
    private val stateEmitter: BehaviorSubject<S> = BehaviorSubject.create()

    /**
     * 패러미터로 받은 `Action`을 런칭한다. 런칭된 Action은 비즈니스로직 을 수행 하는 등 작업 후
     * View를 업데이트 하기 위한 `State`로 생성 되거나 다른 Action이 발행될 수 있다.
     */
    abstract fun dispatcher(action: A)

    /**
     * 런칭된 `Action`을 처리 하고 `State`를 반환 한다.
     * 비즈니스 로직이나 기타 분기 로직등을 통해 `Action`은 success/failed `Action`이 되거나
     * 뷰를 업데이트 하기 위한 `State`들을 발행 한다. 발행된 `State`는 `stateSubscriber()`메소드를
     * 통해 발행될 옵저버를 구독 하면 된다.
     */
    protected abstract fun actionHandler(action: A): S

    /**
     * 발행된 `State`를 구독 하고 리스너 인스턴스를 반환한다.
     */
    fun stateSubscriber(): Observable<S> =
        stateEmitter.hide().observeOn(AndroidSchedulers.mainThread())

}