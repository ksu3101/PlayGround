package com.swkang.playground.base

import android.os.Bundle
import android.os.Handler
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.swkang.model.base.helper.MessageHelper
import com.swkang.playground.R
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {
    @Inject
    lateinit var messageHelper: MessageHelper
    private var onBackBtnPressed = false

    @LayoutRes
    abstract fun getLayoutId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
    }

    @CallSuper
    override fun onBackPressed() {
        if (onBackBtnPressed) {
            super.onBackPressed()
            return
        }
        onBackBtnPressed = true
        messageHelper.showToast(R.string.c_exit)
        Handler().postDelayed({
            onBackBtnPressed = false
        }, 2000)
    }

}