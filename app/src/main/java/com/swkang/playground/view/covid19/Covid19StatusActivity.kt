package com.swkang.playground.view.covid19

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.swkang.playground.R
import com.swkang.playground.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.covid19status_activity.*

@AndroidEntryPoint
class Covid19StatusActivity : BaseActivity() {
    private val maxPages = 2
    private lateinit var vPager: ViewPager2

    override fun getLayoutId(): Int = R.layout.covid19status_activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vPager = covid19status_viewpager
        vPager.adapter = Covid19StatusPagerAdapter(this)

        initialzeTabLayouts()
    }

    override fun onBackPressed() {
        if (!::vPager.isInitialized || vPager.currentItem == 0) {
            super.onBackPressed()
        } else {
            vPager.currentItem - 1
        }
    }

    private fun initialzeTabLayouts() {
        TabLayoutMediator(covid19status_tab, vPager) { tab, position ->
            tab.text = getString(
                if (position == 0) R.string.covid19_status_korea_title
                else R.string.covid19_status_world_title
            )
        }.attach()
    }

    /**
     * ViewPager2 Adapter class.
     */
    private inner class Covid19StatusPagerAdapter(
        fa: FragmentActivity
    ) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = maxPages

        override fun createFragment(position: Int): Fragment =
            Covid19StatusFragment(position == 0)
    }

}