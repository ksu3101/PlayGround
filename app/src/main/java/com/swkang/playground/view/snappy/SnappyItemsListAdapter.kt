package com.swkang.playground.view.snappy

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.swkang.model.domain.snappy.Book
import com.swkang.model.domain.snappy.RvItem

data class BestSeller(
    override val data: Book,
    override val viewType: Int
): RvItem<Book>

data class NewBook(
    override val data: Book,
    override val viewType: Int
): RvItem<Book>


class SnappyItemsListAdapter
    : ListAdapter<RvItem<Book>, RecyclerView.ViewHolder>(diffUtil) {

    companion object {
        const val VIEWTYPE_CATEGORY = 0
        const val VIEWTYPE_BESTSELLER = 1
        const val VIEWTYPE_NEW = 2
        const val VIEWTYPE_RECOMMENDED = 3
        const val VIEWTYPE_DEFAULT = 4

        val diffUtil = object : DiffUtil.ItemCallback<RvItem<Book>>() {
            override fun areItemsTheSame(
                oldItem: RvItem<Book>,
                newItem: RvItem<Book>
            ) = oldItem.data.id == newItem.data.id

            override fun areContentsTheSame(
                oldItem: RvItem<Book>,
                newItem: RvItem<Book>
            ) = oldItem.data == newItem.data
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    }

    override fun getItemViewType(position: Int): Int =
        getItem(position).viewType

}