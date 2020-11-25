package com.swkang.common.exts

fun String?.isNumber(): Boolean {
    return if (this.isNullOrEmpty()) false
    else this.matches("\\d+".toRegex())
}
