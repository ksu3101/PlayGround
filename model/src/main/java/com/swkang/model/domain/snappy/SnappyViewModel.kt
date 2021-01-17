package com.swkang.model.domain.snappy

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.swkang.common.exts.rx.subscribeAndDispose
import com.swkang.model.base.BaseViewModel
import com.swkang.model.base.ui.RvItem
import com.swkang.model.base.ui.TextCategoryItem
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * https://medium.com/over-engineering/detecting-snap-changes-with-androids-recyclerview-snaphelper-9e9f5e95c424
 */

data class Book(
    override val id: Long,
    val name: String
) : RvItem

class SnappyViewModel : BaseViewModel() {
    companion object {
        val MOCK_BOOK_ITEMS = listOf(
            Book(0, "총, 균, 쇠"),
            TextCategoryItem(1, "베스트 셀러")
        )
    }

    private val _items = MutableLiveData<List<RvItem>>(emptyList())
    val items: LiveData<List<RvItem>>
        get() = _items

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    init {
        requestBookItems()
    }

    private fun requestBookItems() {
        Single.just(MOCK_BOOK_ITEMS)
            .delay((1..4L).random(), TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { _isLoading.postValue(true) }
            .doFinally { _isLoading.postValue(false) }
            .subscribeAndDispose(
                this,
                {
                    _items.value = it
                }, {
                    // nothing to do.
                }
            )
    }

}