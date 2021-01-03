package com.swkang.common.exts

import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

inline fun <T1, T2, R> Any.ifNotNull(t1: T1?, t2: T2?, block: (T1, T2) -> R?): R? {
    return if (t1 != null && t2 != null) block(t1, t2) else null
}