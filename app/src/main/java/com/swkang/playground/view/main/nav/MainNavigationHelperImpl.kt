package com.swkang.playground.view.main.nav

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.swkang.model.domain.main.nav.MainNavigationHelper
import com.swkang.playground.R
import com.swkang.playground.view.main.MainFragmentDirections

class MainNavigationHelperImpl(
    private val activity: AppCompatActivity
): MainNavigationHelper {

    override fun openCovid19StatusPage() {
        val direction = MainFragmentDirections
            .actionMainFragmentToCovid19StatusFragment()
        activity.findNavController(R.id.fragmentContainer).navigate(direction)
        activity.supportActionBar?.hide()
    }

    override fun closeCovid19StatusPage() {
        activity.findNavController(R.id.fragmentContainer).navigateUp()
        activity.supportActionBar?.show()
    }

}