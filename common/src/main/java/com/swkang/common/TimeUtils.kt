package com.swkang.common

import java.text.SimpleDateFormat
import java.util.*

/**
 * 현재 시간에 대한 문자열을 얻는다.
 * 시간 문자열의 타입은 `yyyy년 MM월 dd일 HH:mm`이다.
 */
fun getTimeStamp(): String = getTimeStamp(DEFAULT_TIMESTAMP)

/**
 * 현재 시간에 대한 문자열을 `pattern`타입으로 얻는다.
 */
fun getTimeStamp(pattern: String): String {
    val sdf = SimpleDateFormat(pattern, Locale.KOREA)
    sdf.timeZone = TimeZone.getTimeZone("Asia/Seoul")
    return sdf.format(Calendar.getInstance().time)
}