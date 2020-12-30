package com.swkang.playground.repository.peopleinspace

import com.swkang.model.base.actionTransformer
import com.swkang.model.base.createActionProcessor
import com.swkang.model.domain.peopleinspace.PeopleInSpaceAction
import com.swkang.model.domain.peopleinspace.PeopleInSpaceState
import com.swkang.model.domain.peopleinspace.InitializeAction
import com.swkang.model.domain.peopleinspace.InitializedState
import com.swkang.model.domain.peopleinspace.PeoplesInSpaceReceiveSuccessState
import com.swkang.model.domain.peopleinspace.PeoplesInSpaceReceiveErrorState
import com.swkang.model.domain.peopleinspace.RetrievePeoplesInSpaceAction
import com.swkang.model.domain.peopleinspace.repository.HowManyPeopleInSpaceRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject

class HowManyPeopleInSpaceRepositoryImpl(
    private val api: HowManyPeopleInSpaceApi,
) : HowManyPeopleInSpaceRepository {
    override val actionEmitter = PublishSubject.create<PeopleInSpaceAction>()

    override val actionProcessor = createActionProcessor<PeopleInSpaceAction, PeopleInSpaceState> {
        arrayOf(
            it.ofType(InitializeAction::class.java).compose(initialized),
            it.ofType(RetrievePeoplesInSpaceAction::class.java).compose(retrievePeoplesInSpace)
        )
    }

    private val initialized = actionTransformer<InitializeAction, PeopleInSpaceState> {
        Observable.just(InitializedState)
    }

    private val retrievePeoplesInSpace =
        actionTransformer<RetrievePeoplesInSpaceAction, PeopleInSpaceState> {
            api.requestPeoplesInSpace()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map<PeopleInSpaceState> {
                    PeoplesInSpaceReceiveSuccessState(
                        it.number!!,
                        it.getPeoples()
                    )
                }
                .onErrorReturn { PeoplesInSpaceReceiveErrorState(it.message!!) }
                .toObservable()
        }

    override fun dispatch(action: PeopleInSpaceAction) {
        actionEmitter.onNext(action)
    }

    override fun stateSubscribe(): Observable<PeopleInSpaceState> =
        actionEmitter.compose(actionProcessor)
            .distinctUntilChanged()
//            .replay(1)
//            .autoConnect()
}