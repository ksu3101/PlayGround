package com.swkang.model.domain.snappy

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.swkang.model.base.BaseViewModel

/**
 * https://medium.com/over-engineering/detecting-snap-changes-with-androids-recyclerview-snaphelper-9e9f5e95c424
 */

interface RvItem<T> {
    val data: T
    val viewType: Int
}

data class Book(
    val id: Long,
    val name: String
)

class SnappyViewModel : BaseViewModel() {

    private val _items = MutableLiveData<List<RvItem<Book>>>()
    val items: LiveData<List<RvItem<Book>>>
        get() = _items

    init {
    }
}