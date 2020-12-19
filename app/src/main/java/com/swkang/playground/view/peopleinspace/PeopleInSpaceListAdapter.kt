package com.swkang.playground.view.peopleinspace

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.swkang.model.domain.peopleinspace.datas.SpacePeople
import com.swkang.playground.R
import kotlinx.android.synthetic.main.howmanypeopleinspace_people_item.view.peoplesinspace_item_tv_loc
import kotlinx.android.synthetic.main.howmanypeopleinspace_people_item.view.peoplesinspace_item_tv_name

class PeopleInSpaceListAdapter :
    ListAdapter<SpacePeople, PeopleInSpaceListAdapter.PeopleInSpaceViewHolder>(diffUtil) {
    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<SpacePeople>() {
            override fun areItemsTheSame(oldItem: SpacePeople, newItem: SpacePeople): Boolean =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: SpacePeople, newItem: SpacePeople): Boolean =
                oldItem == newItem

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleInSpaceViewHolder =
        PeopleInSpaceViewHolder.create(parent)

    override fun onBindViewHolder(holder: PeopleInSpaceViewHolder, position: Int) =
        holder.bind(getItem(position))

    class PeopleInSpaceViewHolder(
        private val binding: ViewDataBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun create(parent: ViewGroup): PeopleInSpaceViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = DataBindingUtil.inflate<ViewDataBinding>(
                    layoutInflater,
                    R.layout.howmanypeopleinspace_people_item,
                    parent,
                    false
                )
                return PeopleInSpaceViewHolder(binding)
            }
        }

        fun bind(peopleInSpace: SpacePeople) {
            binding.root.peoplesinspace_item_tv_loc.text = peopleInSpace.craft
            binding.root.peoplesinspace_item_tv_name.text = peopleInSpace.name
        }
    }
}