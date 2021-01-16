package com.swkang.playground.base.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.swkang.model.base.ui.RvItem

abstract class MultipleItemTypedListAdater
    : ListAdapter<RvItem, RecyclerView.ViewHolder>(defaultDiffUtil) {
    companion object {
        private val defaultDiffUtil = object : DiffUtil.ItemCallback<RvItem>() {
            override fun areItemsTheSame(
                oldItem: RvItem,
                newItem: RvItem
            ) = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: RvItem,
                newItem: RvItem
            ) = oldItem.equals(newItem)
        }
    }
}

abstract class TypedViewHolder<in T : RvItem>(
    binding: ViewDataBinding
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun getViewDataBinding(parent: ViewGroup, @LayoutRes itemViewId: Int): ViewDataBinding {
            val layoutInflater = LayoutInflater.from(parent.context)
            return DataBindingUtil.inflate(
                layoutInflater,
                itemViewId,
                parent,
                false
            )
        }
    }

    abstract fun bind(data: T)
}