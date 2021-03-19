package com.swkang.model.domain.rendereffects

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.swkang.model.base.BaseViewModel

class RenderEffectFragmentVM : BaseViewModel() {
    private val _isBlurEnable = MutableLiveData(false)
    val isBlurEnable: LiveData<Boolean>
        get() = _isBlurEnable

    private val _isFullScreen = MutableLiveData(false)
    val isFullScreen: LiveData<Boolean>
        get() = _isFullScreen

    private val _xValue = MutableLiveData(0)
    val xValue: LiveData<Int>
        get() = _xValue

    private val _yValue = MutableLiveData(0)
    val yValue: LiveData<Int>
        get() = _yValue

}