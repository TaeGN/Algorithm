package AtCoder.ProblemList.Difficulty400_799.SuperTakahashiBros

import java.util.PriorityQueue

const val IMPOSSIBLE = Long.MAX_VALUE shr 2
fun main() {
    val N = readln().trim().toInt()
    val outLists = List(N) { mutableListOf<Pair<Int, Int>>() }
    repeat(N - 1) { idx ->
        val (A, B, X) = readln().trim().split(" ").map(String::toInt)
        outLists[idx].add((idx + 1) to A)
        outLists[idx].add((X - 1) to B)
    }
    fun result(): Long {
        val dp = LongArray(N) { IMPOSSIBLE }.apply { this[0] = 0 }
        val pq = PriorityQueue<Pair<Int, Long>>(compareBy { it.second })
        pq.add(0 to dp[0])
        while (pq.isNotEmpty()) {
            val (from, time) = pq.poll()
            if (time > dp[from]) continue
            if (from == N - 1) return time
            for ((to, weight) in outLists[from]) {
                if (dp[to] > dp[from] + weight) {
                    dp[to] = dp[from] + weight
                    pq.add(to to dp[to])
                }
            }
        }
        return -1
    }
    println(result())
}