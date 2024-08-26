package 백준.Gold.G3.p30059_YouTube

import java.util.PriorityQueue

fun main() {
    val (N, V) = readln().split(" ").map(String::toInt)
    val pq = PriorityQueue<Pair<Int, Int>>(compareBy { it.first.toDouble() / it.second })
    repeat(N) {
        val (r, t) = readln().split(" ").map(String::toInt)
        pq.add(t to r)
    }
    fun result(): Long {
        if (pq.sumOf { it.second } < V) return -1
        var result = 0L
        var maxOne = -1
        var v = 0
        while (v < V - 1) {
            val (t, r) = pq.poll()
            v += r
            result += t
            if (r == 1) maxOne = t
        }
        if (v == V - 1) {
            var value = Int.MAX_VALUE shr 2
            val pq1 = pq.filter { it.second == 1 }
            val pq2 = pq.filter { it.second == 2 }
            if (pq1.isNotEmpty()) value = minOf(value, pq1.minOf { it.first })
            if (pq2.isNotEmpty() && maxOne != -1) value = minOf(value, -maxOne + pq2.minOf { it.first })
            if (value == Int.MAX_VALUE shr 2) return -1
            result += value
        }
        return result
    }

    println(result())
}