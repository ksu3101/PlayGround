package kr.swkang.common.exts

/**
 * @author bmo
 * @since 2023-03-26
 */

fun String.isNumber(): Boolean =
    (this.isNotEmpty() && this.matches(Regex("\\d+")))
