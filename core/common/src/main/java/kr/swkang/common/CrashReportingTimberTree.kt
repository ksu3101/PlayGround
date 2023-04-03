package kr.swkang.common

import android.util.Log
import timber.log.Timber

/**
 * 디버그 앱 이 아닌 경우, 에러의 정도에 따라서 리포팅을 하거나 하지 않게 처리 한다.
 *
 * @author bmo
 * @since 2023-04-03
 */
class CrashReportingTimberTree : Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        when (priority) {
            Log.VERBOSE,
            Log.DEBUG -> {
                // do nothing
                return
            }
            Log.ERROR -> {
                t?.let {
                    // send error report.
                }
            }
            Log.WARN -> {
                t?.let {
                    // send warning message.
                }
            }
        }
    }
}
