package com.swkang.playground.view.snappy

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.swkang.model.domain.snappy.Book
import com.swkang.model.domain.snappy.SnappyItem

data class BestSeller(
    override val item: Book,
    override val viewType: Int
): SnappyItem<Book>

class SnappyItemsListAdapter
    : ListAdapter<SnappyItem<Book>, RecyclerView.ViewHolder>(diffUtil) {

    companion object {
        const val VIEWTYPE_CATEGORY = 0
        const val VIEWTYPE_BESTSELLER = 1
        const val VIEWTYPE_NEW = 2
        const val VIEWTYPE_RECOMMENDED = 3
        const val VIEWTYPE_DEFAULT = 4

        val diffUtil = object : DiffUtil.ItemCallback<SnappyItem<Book>>() {
            override fun areItemsTheSame(
                oldItem: SnappyItem<Book>,
                newItem: SnappyItem<Book>
            ) = oldItem.item.id == newItem.item.id

            override fun areContentsTheSame(
                oldItem: SnappyItem<Book>,
                newItem: SnappyItem<Book>
            ) = oldItem.item == newItem.item

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    }

    override fun getItemViewType(position: Int): Int =
        getItem(position).viewType

}