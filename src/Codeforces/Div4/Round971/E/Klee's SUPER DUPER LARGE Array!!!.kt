package Codeforces.Div4.Round971.E

import kotlin.math.abs

fun main() {
    val sb = StringBuilder()
    repeat(readln().toInt()) {
        val (N, K) = readln().trim().split(" ").map(String::toInt)
        fun left(i: Long) = K * i + i * (i - 1) / 2
        fun right(i: Long) = (N - i) * K + (N - i) * (i + N - 1) / 2
        fun diff(i: Long) = right(i) - left(i)
        fun isPlus(i: Long): Boolean = diff(i) >= 0
        fun search(start: Long = 0, end: Long = N.toLong()): Long {
            val mid = (start + end) / 2
            if (start == mid) return if (isPlus(end)) end else start
            return if (isPlus(mid)) search(mid, end)
            else search(start, mid - 1)
        }

        val i = search()
        val result = minOf(abs(diff(i)), abs(diff(i - 1)), abs(diff(i + 1)))
        sb.appendLine(result)
    }
    println(sb)
}