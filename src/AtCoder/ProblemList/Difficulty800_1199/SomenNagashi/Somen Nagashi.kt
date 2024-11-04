package AtCoder.ProblemList.Difficulty800_1199.SomenNagashi

import java.util.PriorityQueue
import java.util.TreeSet

fun main() {
    val (N, M) = readln().trim().split(" ").map(String::toInt)
    val pq = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })
    val result = LongArray(N)
    val set = TreeSet((0 until N).toSet())
    repeat(M) {
        val (T, W, S) = readln().trim().split(" ").map(String::toInt)
        while (pq.isNotEmpty() && T >= pq.first().second) set.add(pq.poll().first)
        if (set.isNotEmpty()) {
            val idx = set.first()
            set.remove(idx)
            result[idx] += W.toLong()
            pq.add(idx to (T + S))
        }
    }
    println(result.joinToString("\n"))
}