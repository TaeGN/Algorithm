package AtCoder.ProblemList.Difficulty800_1199.`Takahashi'sSolitaire`

import java.util.TreeMap

fun main() {
    val (N, M) = readln().trim().split(" ").map(String::toInt)
    val A = readln().trim().split(" ").map(String::toInt)
    val map = TreeMap<Int, Int>()
    A.forEach { map.compute(it % M) { _, v -> if (v == null) 1 else v + 1 } }
    fun result(): Long {
        if (M <= N && map.keys == (0 until M).toSet()) return 0
        val total = A.sumOf { it.toLong() }
        var maxSum = 0L
        var curSum = 0L
        var prev = -1
        for ((x, count) in map) {
            if (x == 0 && (M - 1) in map) {
                var i = M
                while (--i in map) curSum += i.toLong() * map[i]!!
            }
            if (prev + 1 == x) curSum += x.toLong() * count
            else curSum = x.toLong() * count
            maxSum = maxOf(maxSum, curSum)
            prev = x
        }
        return (total - maxSum)
    }
    println(result())
}