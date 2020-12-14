package com.swkang.playground.view.covid19

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.swkang.model.domain.covid19.Covid19StatusViewModel
import com.swkang.model.domain.covid19.datas.LocationCovid19Infos
import com.swkang.playground.BR
import com.swkang.playground.R

class Covid19StatusListAdapter(
    private val vm: Covid19StatusViewModel
) : ListAdapter<LocationCovid19Infos, Covid19StatusListAdapter.Covid19StatusViewHolder>(difUtil) {

    // Diff util
    companion object {
        const val VIEWTYPE_TOP = 0
        const val VIEWTYPE_DEFAULT = 1

        val difUtil = object : DiffUtil.ItemCallback<LocationCovid19Infos>() {
            override fun areItemsTheSame(
                oldItem: LocationCovid19Infos,
                newItem: LocationCovid19Infos
            ): Boolean {
                return oldItem.date == newItem.date
            }

            override fun areContentsTheSame(
                oldItem: LocationCovid19Infos,
                newItem: LocationCovid19Infos
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Covid19StatusViewHolder =
        Covid19StatusViewHolder.create(parent, viewType)

    override fun onBindViewHolder(holder: Covid19StatusViewHolder, position: Int) {
        if (holder is Covid19StatusTopViewHolder) holder.bind(vm)
        else if (holder is Civud18StatusCountryViewHolder) holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int =
        when (position) {
            0 -> VIEWTYPE_TOP
            else -> VIEWTYPE_DEFAULT
        }

    /**
     * 뷰타입에 따른 각 뷰홀더의 부모 클래스
     */
    abstract class Covid19StatusViewHolder(
        binding: ViewDataBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun create(parent: ViewGroup, viewType: Int): Covid19StatusViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val viewResId = when (viewType) {
                    VIEWTYPE_TOP -> R.layout.covid19status_item_top
                    VIEWTYPE_DEFAULT -> R.layout.covid19status_item_country
                    else -> throw IllegalArgumentException("`$viewType` is not available view type integer.")
                }
                val binding = DataBindingUtil.inflate<ViewDataBinding>(
                    layoutInflater,
                    viewResId,
                    parent,
                    false
                )
                return if (viewType == VIEWTYPE_TOP) Covid19StatusTopViewHolder(binding)
                else Civud18StatusCountryViewHolder(binding)
            }
        }
    }

    /**
     * 상단 헤더 뷰 홀더
     *
     * - 국내/세계 감염자, 사망자, 완치자 현황
     * - 시-도/각 국가 별 현황 리스트 목록의 헤더
     * - 목록에서 0번째 포지션에 무조건 한개만 존재
     */
    class Covid19StatusTopViewHolder(private val binding: ViewDataBinding) :
        Covid19StatusViewHolder(binding) {
        fun bind(vm: Covid19StatusViewModel) {
            binding.setVariable(BR.vm, vm)
            binding.executePendingBindings()
        }
    }

    /**
     * 시-도/각 국가 별 현황 리스트 아이템 항목에 대한 뷰 홀더
     */
    class Civud18StatusCountryViewHolder(private val binding: ViewDataBinding) :
        Covid19StatusViewHolder(binding) {
        fun bind(covid19Location: LocationCovid19Infos) {
            binding.setVariable(BR.covid19Location, covid19Location)
            binding.executePendingBindings()
        }
    }

}