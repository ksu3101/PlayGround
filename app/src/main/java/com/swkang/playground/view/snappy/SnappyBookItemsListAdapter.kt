package com.swkang.playground.view.snappy

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.swkang.model.base.ui.TextCategoryItem
import com.swkang.model.domain.snappy.Book
import com.swkang.playground.R
import com.swkang.playground.base.ui.MultipleItemTypedListAdater
import com.swkang.playground.base.ui.TypedViewHolder

class SnappyBookItemsListAdapter : MultipleItemTypedListAdater() {
    companion object {
        const val VIEWTYPE_CATEGORY = 0
        const val VIEWTYPE_BOOK = 99
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder =
        when (viewType) {
            VIEWTYPE_BOOK -> BookViewHolder.create(parent)
            VIEWTYPE_CATEGORY -> BookCategoryViewHolder.create(parent)
            else -> throw IllegalArgumentException("unknown viewType. viewtype is [$viewType].")
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is BookViewHolder -> holder.bind(getBook(position))
            is BookCategoryViewHolder -> holder.bind(getCategory(position))
        }
    }

    override fun getItemViewType(position: Int): Int =
        when (getItem(position)) {
            is TextCategoryItem -> VIEWTYPE_CATEGORY
            is Book -> VIEWTYPE_BOOK
            else -> throw IllegalArgumentException("unknown viewType. at [$position].")
        }

    fun getBook(position: Int): Book {
        val book = getItem(position)
        return book as? Book
            ?: throw IllegalStateException("[$position] item is not Book instance. [$book]")
    }

    fun getCategory(position: Int): TextCategoryItem {
        val category = getItem(position)
        return category as? TextCategoryItem
            ?: throw IllegalStateException("[$position] item is not TextCategoryItem instance. [$category]")
    }

    /**
     * Book View Holder
     */
    class BookViewHolder(
        private val binding: ViewDataBinding
    ) : TypedViewHolder<Book>(binding) {
        companion object {
            fun create(parent: ViewGroup): BookViewHolder =
                BookViewHolder(getViewDataBinding(parent, R.layout.snappy_fragment))
        }

        override fun bind(data: Book) {
//            binding.setVariable(BR.book, data)
//            binding.executePendingBindings()
        }
    }

    /**
     * Simple text category View Holder
     */
    class BookCategoryViewHolder(
        private val binding: ViewDataBinding
    ) : TypedViewHolder<TextCategoryItem>(binding) {
        companion object {
            fun create(parent: ViewGroup): BookCategoryViewHolder =
                BookCategoryViewHolder(getViewDataBinding(parent, R.layout.snappy_book_item))
        }

        override fun bind(data: TextCategoryItem) {
//            binding.setVariable(BR.category, data)
//            binding.executePendingBindings()
        }
    }

}