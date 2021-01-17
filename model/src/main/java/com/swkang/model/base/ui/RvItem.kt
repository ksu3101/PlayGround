package com.swkang.model.base.ui

/**
 * `MultipleItemTypedListAdater`에서 사용하는 리스트 데이터 타입의 기본 타입.
 * `id`는 `DiffUtil`에서 각 아이템들을 구분하는데 사용 된다.
 */
interface RvItem {
    val id: Long
}

/**
 * `MultipleItemTypedListAdater`에서 사용되는 카테고리의 기본 제공 타입.
 */
data class TextCategoryItem(
    override val id: Long,
    val data: String
) : RvItem
