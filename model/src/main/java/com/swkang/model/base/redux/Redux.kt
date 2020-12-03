package com.swkang.model.base.redux

import io.reactivex.rxjava3.core.Observable

/**
 * `State`를 생성하기 위한 Trigger Action 혹은 Result Action.
 * - Result Action은 success 혹은 failed상태 일 수 있다.
 */
interface Action

/**
 * 화면의 상태를 정의한 `State`. `
 * - StateViewModel`을 상속한 경우 화면을 갱신하기 위해서는 무조건 `State`를 전달 받아야만 한다.
 */
interface State {
    fun getStateKey(): String
}

/**
 * 최신 `State`와 전달받은 result`Action`을 통해 새로운 상태를 `State`로 생성해주는 리듀서.
 */
interface Reducer<S : State> {
    fun reduce(oldState: S, resultAction: Action): S
}

/**
 * Action dispatcher function.
 */
typealias Dispatcher = (Action) -> Unit

/**
 * `State`를 저장 하고 `Action`을 dispatching 해주는 `Store`.
 */
interface Store<S : State> {
    /**
     * 생성한 `Action`을 미들웨어와 리듀서를 통해 새로운 `State`를 생성하도록 한다.
     * - `Action`은 핸들링 여부에 따라서 result Action, success-failed Action이 될 수 있다.
     * - `Action`은 핸들링 여부에 따라서 컨슘되어 제거 될 수도 있다.
     */
    fun dispatch(action: Action)

    /**
     * 리듀서를 통해서 생성된 `State`를 전달 받을 리스너 인스턴스를 얻는다.
     */
    fun stateListener(): Observable<S>
}


interface Middleware<S : State> {
    fun create(store: Store<S>, next: Dispatcher): Dispatcher
}

/**
 * 디스패치된 `Action`을 핸들링 하여 새로운 `Action`을 생성 한다.
 * - 새로운 Action을 생성하지 않고 기존 Action을 그대로 전달 하거나 아예 컨슘할 수도 있다.
 */
interface ActionProcessor<S : State> {
    fun run(action: Observable<Action>, store: Store<S>): Observable<out Action>
}