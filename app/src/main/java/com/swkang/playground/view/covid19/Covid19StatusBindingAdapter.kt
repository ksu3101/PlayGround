package com.swkang.playground.view.covid19

import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.swkang.common.exts.toCommaNumberString
import com.swkang.model.domain.covid19.Covid19StatusViewModel
import com.swkang.model.domain.covid19.datas.LocationCovid19Infos
import com.swkang.playground.R

@BindingAdapter(value = ["viewModel", "covid19Locations"])
fun setUpCovid19StatusRecyclerView(
    rv: RecyclerView,
    vm: Covid19StatusViewModel,
    locations: List<LocationCovid19Infos>
) {
    var adapter = rv.adapter
    if (adapter == null || adapter !is Covid19StatusListAdapter) {
        adapter = Covid19StatusListAdapter(vm)
        rv.adapter = adapter
    }
    adapter.submitList(locations)
}

/**
 * TextView를 대상으로 주어진 `value`의 증감에 대해 스타일과 텍스트 등을 적용 한다.
 *
 * - `value`가 0 보다 크면 붉은색 폰트 컬러, 그리고 prefix로 `+ `가 붙는다.
 * - `value`가 0보다 적거나 같으면 녹색 폰트 컬러를 적용 한다.
 */
@BindingAdapter(value = ["covid19AddedNumbers", "isPositiveByAboveFromZero"])
fun setTextWithPositiveStatusByDatas(
    tv: TextView,
    value: Long,
    isPositiveByAboveFromZero: Boolean
) {
    tv.setTextColor(
        ContextCompat.getColor(
            tv.context,
            if (isPositiveByAboveFromZero) {
                if (value <= 0) R.color.covid_font_positive
                else R.color.covid_font_negative
            } else {
                if (value > 0) R.color.covid_font_negative
                else R.color.covid_font_positive
            }
        )
    )
    val displayedText =
        "${if (value > 0) "+ " else ""}${if (value > 0) value.toCommaNumberString() else value.toString()}"
    tv.setText(displayedText)
}