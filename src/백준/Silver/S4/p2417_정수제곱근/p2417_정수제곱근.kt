package 백준.Silver.S4.p2417_정수제곱근

import kotlin.math.sqrt

fun main() {
    fun Long.sqrt(): Long {
        fun Long.binarySearch(start: Long = 0, end: Long = sqrt(Long.MAX_VALUE.toDouble()).toLong()): Long {
            if (start > end) return start
            val mid = (start + end) / 2
            if (mid * mid < this) return this.binarySearch(mid + 1, end)
            return this.binarySearch(start, mid - 1)
        }
        return this.binarySearch()
    }
    readln().toLong().sqrt().let(::println)
}