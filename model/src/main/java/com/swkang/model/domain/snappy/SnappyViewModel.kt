package com.swkang.model.domain.snappy

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.swkang.model.base.BaseViewModel
import com.swkang.model.base.ui.RvItem

/**
 * https://medium.com/over-engineering/detecting-snap-changes-with-androids-recyclerview-snaphelper-9e9f5e95c424
 */


data class Book(
    override val id: Long,
    val name: String
): RvItem

class SnappyViewModel : BaseViewModel() {

    private val _items = MutableLiveData<List<Book>>(emptyList())
    val items: LiveData<List<Book>>
        get() = _items

    init {
    }
}