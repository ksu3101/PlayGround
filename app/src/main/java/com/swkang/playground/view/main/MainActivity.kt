package com.swkang.playground.view.main

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.swkang.playground.R
import com.swkang.playground.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.main_activity.*

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    override fun getLayoutId(): Int = R.layout.main_activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(main_toolbar)

        val navController = findNavController(R.id.fragmentContainer)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }
}