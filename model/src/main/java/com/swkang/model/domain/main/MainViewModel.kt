package com.swkang.model.domain.main

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import com.swkang.model.base.BaseViewModel
import com.swkang.model.domain.main.nav.MainNavigationHelper

class MainViewModel @ViewModelInject constructor(
    private val navigation: MainNavigationHelper,
    @Assisted private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    val onCovid19StatusBtnClicked: () -> Unit = {
        navigation.openCovid19StatusPage()
    }

}