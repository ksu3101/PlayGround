package com.swkang.model.domain.main

import androidx.lifecycle.SavedStateHandle
import com.swkang.model.base.BaseViewModel
import com.swkang.model.domain.main.nav.MainNavigationHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val navigation: MainNavigationHelper
) : BaseViewModel() {

    val onCovid19StatusBtnClicked: () -> Unit = {
        navigation.openCovid19StatusPage()
    }

    val onHowManyPeoplesInSpaceBtnClicked: () -> Unit = {
        navigation.openHowManyPeoplesInSpacePage()
    }

    val onSnappyRecyclerViewExampleBtnClicked: () -> Unit = {
        
    }

    val onContractsExampleBtnClicked: () -> Unit = {

    }

    val onRenderEffectsExampleBtnClicked: () -> Unit = {

    }

}