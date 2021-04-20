package com.swkang.playground.view.rendereffects

import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.RenderEffect
import android.graphics.Shader
import android.view.View
import androidx.annotation.RequiresApi
import androidx.databinding.BindingAdapter

@RequiresApi(31)
@BindingAdapter(value = ["isBlurEnabled", "xValue", "yValue"])
fun setBlurRenderEffects(view: View, isBlurEnabled: Boolean, xValue: Float, yValue: Float) {
    view.setRenderEffect(
        if (isBlurEnabled) createBlurEffect(xValue, yValue)
        else null
    )
}

@RequiresApi(31)
@BindingAdapter(value = ["isDesaturateEnabled", "saturationValue"])
fun setDesaturateEffect(view: View, isDesaturateEnabled: Boolean, saturationValue: Float) {
    view.setRenderEffect(
        if (isDesaturateEnabled) {
            RenderEffect.createChainEffect(
                createBlurEffect(10.0f, 10.0f),
                createDesaturateEffect(saturationValue)
            )
        } else null
    )
}

@RequiresApi(31)
private fun createBlurEffect(xValue: Float, yValue: Float): RenderEffect =
    RenderEffect.createBlurEffect(xValue, yValue, Shader.TileMode.MIRROR)

@RequiresApi(31)
private fun createDesaturateEffect(saturationValue: Float): RenderEffect =
    RenderEffect.createColorFilterEffect(
        ColorMatrixColorFilter(
            ColorMatrix().apply {
                setSaturation(saturationValue)
            }
        )
    )