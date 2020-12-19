package com.swkang.playground.repository.peopleinspace

import com.swkang.model.base.actionTransformer
import com.swkang.model.base.createActionProcessor
import com.swkang.model.domain.peopleinspace.HowManyPeopleInSpaceAction
import com.swkang.model.domain.peopleinspace.HowManyPeopleInSpaceState
import com.swkang.model.domain.peopleinspace.InitializeAction
import com.swkang.model.domain.peopleinspace.InitializedState
import com.swkang.model.domain.peopleinspace.PeoplesInSpaceRetrieveCompleteState
import com.swkang.model.domain.peopleinspace.PeoplesInSpaceRetrieveErrorState
import com.swkang.model.domain.peopleinspace.RetrievePeoplesInSpaceAction
import com.swkang.model.domain.peopleinspace.repository.HowManyPeopleInSpaceRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject

class HowManyPeopleInSpaceRepositoryImpl(
    private val api: HowManyPeopleInSpaceApi,
) : HowManyPeopleInSpaceRepository {

    private val actionPublisher = PublishSubject.create<HowManyPeopleInSpaceAction>()

    override val actionProcessor = createActionProcessor {
        arrayOf(
            it.ofType(InitializeAction::class.java).compose(initialized),
            it.ofType(RetrievePeoplesInSpaceAction::class.java).compose(retrievePeoplesInSpace)
        )
    }

    private val initialized = actionTransformer {
        Observable.just(InitializedState)
    }

    private val retrievePeoplesInSpace = actionTransformer {
        api.requestPeoplesInSpace()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map<HowManyPeopleInSpaceState> {
                PeoplesInSpaceRetrieveCompleteState(
                    it.number!!,
                    it.getPeoples()
                )
            }
            .onErrorReturn { PeoplesInSpaceRetrieveErrorState(it.message!!) }
            .toObservable()
    }

    override fun dispatch(action: HowManyPeopleInSpaceAction) {
        actionPublisher.onNext(action)
    }

    override fun stateSubscriber(): Observable<HowManyPeopleInSpaceState> =
        actionPublisher.compose(actionProcessor)
            .distinctUntilChanged()
//            .replay(1)
//            .autoConnect()
}