package 백준.Silver.S1.p1931_회의실배정

import java.util.PriorityQueue

fun main() {
    val N = readln().toInt()
    val pq = PriorityQueue<Pair<Int, Int>>(compareBy({ it.second }, { it.first }))
    repeat(N) { readln().split(" ").map(String::toInt).let { pq.add(it[0] to it[1]) } }
    var time = 0
    var result = 0
    while (pq.isNotEmpty()) {
        val (start, end) = pq.poll()
        if (time > start) continue
        result++
        time = end
    }
    println(result)
}