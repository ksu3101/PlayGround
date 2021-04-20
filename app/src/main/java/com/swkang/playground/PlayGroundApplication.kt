package com.swkang.playground

import android.app.ActivityManager
import android.content.Context
import android.os.Build
import android.webkit.WebView
import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp

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

}