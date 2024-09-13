package 백준.Gold.G2.p10543_ZABAVA

import java.util.PriorityQueue

fun main() {
    val (N, M, K) = readln().split(" ").map(String::toInt)
    val countArr = IntArray(M)
    repeat(N) { countArr[readln().toInt() - 1]++ }
    fun noise(pair: Pair<Int, Int>): Long {
        var noise = 0L
        val k = pair.first.toLong() / pair.second
        noise += (1 + k) * k / 2 * pair.second
        noise += (k + 1) * (pair.first % pair.second)
        return noise
    }

    val pq = PriorityQueue<Pair<Int, Int>>(compareBy { -(noise(it) - noise(it.copy(second = it.second + 1))) })
    countArr.forEach { pq.add(it to 1) }
    repeat(K) { pq.add(pq.poll().let { it.copy(second = it.second + 1) }) }
    println(pq.sumOf { noise(it) })
}