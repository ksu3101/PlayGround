package com.swkang.playground

import android.app.ActivityManager
import android.content.Context
import android.os.Build
import android.webkit.WebView
import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp
import io.reactivex.rxjava3.exceptions.UndeliverableException
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import java.io.IOException
import java.lang.IllegalStateException
import java.lang.NullPointerException
import java.net.SocketException

@HiltAndroidApp
class PlayGroundApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val processName = getProcessName(this)
            val packageName = this.packageName
            if (!processName.isNullOrEmpty() && !packageName.equals(processName)) {
                WebView.setDataDirectorySuffix(processName)
            }
        }

        setUpRxErrorHandler()
    }

    fun getProcessName(context: Context): String? {
        val manager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager?
        if (manager == null) return null
        for (p in manager.runningAppProcesses) {
            if (p.pid == android.os.Process.myPid()) {
                return p.processName
            }
        }
        return null
    }

    private fun setUpRxErrorHandler() {
        RxJavaPlugins.setErrorHandler {
            var error = it
            if (error is UndeliverableException) {
                error = it.cause
            }
            if (error is IOException || error is SocketException) {
                return@setErrorHandler
            }
            if (error is InterruptedException) {
                return@setErrorHandler
            }
            if (error is NullPointerException || error is IllegalArgumentException) {
                Thread.currentThread().uncaughtExceptionHandler
                    .uncaughtException(Thread.currentThread(), error)
                return@setErrorHandler
            }
            if (error is IllegalStateException) {
                Thread.currentThread().uncaughtExceptionHandler
                    .uncaughtException(Thread.currentThread(), error)
                return@setErrorHandler
            }
        }
    }

}