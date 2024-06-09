package 백준.Gold.G1.p1300_K번째수

import kotlin.math.min

fun main() {
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toLong()
    val k = br.readLine().toLong()

    fun search(start: Long, end: Long): Long {
        if(start > end) return start
        val mid = (start + end) / 2
        val count = (1..min(mid, n)).fold(0L) { acc: Long, i: Long -> acc + min(mid / i, n) }
        return if (count < k) search(mid + 1, end)
        else search(start, mid - 1)
    }

    search(1, k).let(::println)
}