package com.swkang.common.exts

/**
 * 주어진 리스트에서 `maxSize`만큼의 원소를 갖는 리스트를 얻으려 한다.
 *
 * - 리스트의 원소가 없을 경우 빈 리스트를 반환 한다.
 * - 리스트의 크기가 주어진 `maxSize`와 같거나 `maxSize`보다 클 경우 리스트를 그대로 얻는다.
 * - 리스트의 크기가 `maxSize`보다 작을 경우 `0`의 인덱스에서 `maxSize`인덱스 까지의 리스트를 얻는다.
 */
fun <T> List<T>.limit(maxSize: Int): List<T> {
    if (maxSize == 0) return emptyList()
    if (this.isEmpty() || this.size == maxSize || this.size < maxSize) return this
    return this.subList(0, maxSize)
}
