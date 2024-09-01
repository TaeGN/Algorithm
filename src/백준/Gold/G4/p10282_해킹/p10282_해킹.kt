package 백준.Gold.G4.p10282_해킹

import java.util.PriorityQueue

const val IMPOSSIBLE = Int.MAX_VALUE
fun main() {
    val sb = StringBuilder()
    val outLists = List(10001) { mutableListOf<Pair<Int, Int>>() }
    val dist = IntArray(10001)
    fun count(n: Int): Int {
        var count = 0
        for (i in 1..n) {
            if (dist[i] != IMPOSSIBLE) count++
        }
        return count
    }

    fun totalTime(n: Int): Int {
        var totalTime = 0
        for (i in 1..n) {
            if (dist[i] != IMPOSSIBLE) totalTime = maxOf(totalTime, dist[i])
        }
        return totalTime
    }

    repeat(readln().toInt()) {
        val (N, D, C) = readln().trim().split(" ").map(String::toInt)
        for (i in 1..N) {
            dist[i] = IMPOSSIBLE
            outLists[i].clear()
        }
        repeat(D) {
            val (A, B, S) = readln().trim().split(" ").map(String::toInt)
            outLists[B].add(A to S)
        }
        val pq = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })
        pq.add(C to 0)
        dist[C] = 0
        while (pq.isNotEmpty()) {
            val (from, totalTime) = pq.poll()
            if (dist[from] < totalTime) continue
            for ((to, time) in outLists[from]) {
                if (dist[to] > totalTime + time) {
                    dist[to] = totalTime + time
                    pq.add(to to dist[to])
                }
            }
        }
        sb.appendLine("${count(N)} ${totalTime(N)}")
    }
    println(sb)
}