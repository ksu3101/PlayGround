package com.swkang.common.exts

/**
 * 주어진 문자열이 숫자로만 이루어져 있는지 확인 한다.
 *
 * - null or "" : false
 * - "123456" : true
 * - "123.456" : false
 * - "abc123" false
 */
fun String?.isNumber(): Boolean {
    return if (this.isNullOrEmpty()) false
    else this.matches("\\d+".toRegex())
}
