package 백준.Gold.G5.p14677_병약한윤호

import kotlin.math.max
import kotlin.math.min

fun main() {
    val N = readln().toInt()
    val s = readln()
    val maxCountMap = mutableMapOf<String, Int>()
    fun String.maxCount(count: Int = 0, firstIdx: Int = 0, lastIdx: Int = length - 1): Int {
        val subStr = substring(firstIdx, lastIdx + 1)
        if(maxCountMap.containsKey(subStr)) return maxCountMap[subStr]!!
        if (count == min(length, N * 3)) return count.also { maxCountMap[subStr] = it }
        val c = when (count % 3) {
            0 -> 'B'
            1 -> 'L'
            else -> 'D'
        }
        var maxCount = count
        if (this[firstIdx] == c) maxCount = max(maxCount, maxCount(count + 1, firstIdx + 1, lastIdx))
        if (this[lastIdx] == c) maxCount = max(maxCount, maxCount(count + 1, firstIdx, lastIdx - 1))
        return maxCount.also { maxCountMap[subStr] = it }
    }
    println(s.maxCount())
}