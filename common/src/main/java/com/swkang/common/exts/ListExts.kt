package com.swkang.common.exts

fun <T> List<T>.sliceList(startIndex: Int, endIndex: Int): List<T> {
    if (this.size in 1..endIndex) return this.subList(startIndex, endIndex)
    return this
}