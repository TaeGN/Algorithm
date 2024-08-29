package 백준.Platinum.P4.p10217_KCMTravel

import java.util.PriorityQueue

const val IMPOSSIBLE = Int.MAX_VALUE shr 2
fun main() {
    repeat(readln().toInt()) {
        val (N, M, K) = readln().trim().split(" ").map(String::toInt)
        val roadLists = List(N + 1) { mutableListOf<Triple<Int, Int, Int>>() }
        repeat(K) {
            val (u, v, c, d) = readln().trim().split(" ").map(String::toInt)
            roadLists[u].add(Triple(v, c, d))
        }
        roadLists.forEach { list -> list.sortWith(compareBy({ it.third }, { it.second })) }
        val dp = Array(N + 1) { IntArray(M + 1) { IMPOSSIBLE } }.apply { this[1][0] = 0 }
        val pq = PriorityQueue<Triple<Int, Int, Int>>(compareBy { it.third })
        pq.add(Triple(1, 0, 0))
        while (pq.isNotEmpty()) {
            val (from, totalPrice, totalTime) = pq.poll()
            if (dp[from][totalPrice] < totalTime) continue
            for ((to, price, time) in roadLists[from]) {
                val newTotalPrice = totalPrice + price
                if (newTotalPrice > M) continue
                val newTotalTime = totalTime + time
                if (dp[to][newTotalPrice] > newTotalTime) {
                    for (nextPrice in newTotalPrice..M) {
                        if (dp[to][nextPrice] <= newTotalTime) break
                        dp[to][nextPrice] = newTotalTime
                    }
                    pq.add(Triple(to, newTotalPrice, newTotalTime))
                }
            }
        }

        println(dp[N].min().let { if (it == IMPOSSIBLE) "Poor KCM" else it })
    }
}