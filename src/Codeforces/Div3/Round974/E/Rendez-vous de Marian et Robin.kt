package Codeforces.Div3.Round974.E

import java.util.PriorityQueue

const val IMPOSSIBLE = Long.MAX_VALUE shr 2
fun main() {
    val sb = StringBuilder()
    repeat(readln().toInt()) {
        val (N, M, H) = readln().split(" ").map(String::toInt)
        val hasHorse = BooleanArray(N)
        readln().split(" ").map(String::toInt).forEach { hasHorse[it - 1] = true }
        val outLists = List(N) { mutableListOf<Pair<Int, Int>>() }
        repeat(M) {
            val (U, V, W) = readln().split(" ").map(String::toInt)
            outLists[U - 1].add((V - 1) to W)
            outLists[V - 1].add((U - 1) to W)
        }
        fun dp(start: Int): Array<LongArray> {
            val dp = Array(2) { LongArray(N) { IMPOSSIBLE } }
            val pq = PriorityQueue<Triple<Int, Int, Long>>(compareBy { it.third })
            val startType = if (hasHorse[start]) 1 else 0
            dp[startType][start] = 0
            pq.add(Triple(startType, start, dp[startType][start]))
            while (pq.isNotEmpty()) {
                val (type, idx, weight) = pq.poll()
                if (weight > dp[type][idx]) continue
                for ((nIdx, w) in outLists[idx]) {
                    val nType = if (type == 1 || hasHorse[nIdx]) 1 else 0
                    val nWeight = dp[type][idx] + (if (type == 1) w / 2 else w)
                    if (dp[nType][nIdx] > nWeight) {
                        dp[nType][nIdx] = nWeight
                        pq.add(Triple(nType, nIdx, nWeight))
                    }
                }
            }
            return dp
        }

        val dp1 = dp(0)
        val dp2 = dp(N - 1)
        var result = IMPOSSIBLE
        for (i in 0 until N) {
            result = minOf(result, maxOf(minOf(dp1.first()[i], dp1.last()[i]), minOf(dp2.first()[i], dp2.last()[i])))
        }
        sb.appendLine(if (result == IMPOSSIBLE) -1 else result)
    }
    println(sb)
}