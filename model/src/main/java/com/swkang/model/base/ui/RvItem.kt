package com.swkang.model.base.ui

interface RvItem {
    val id: Long
}

data class TextCategoryItem(
    override val id: Long,
    val data: String
) : RvItem
