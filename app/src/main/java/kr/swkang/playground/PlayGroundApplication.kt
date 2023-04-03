package kr.swkang.playground

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import kr.swkang.common.CrashReportingTimberTree
import timber.log.Timber

/**
 * @author bmo
 * @since 2023-03-08
 */
@HiltAndroidApp
class PlayGroundApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(
            if (BuildConfig.DEBUG) {
                Timber.DebugTree()
            } else {
                CrashReportingTimberTree()
            }
        )
    }
}
