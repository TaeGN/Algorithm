package AtCoder.ABC.ABC355.D

import java.util.PriorityQueue

fun main() {
    val N = readln().toInt()
    val pq = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })
    val arr = Array(N) { readln().trim().split(" ").map(String::toInt).let { it[0] to it[1] } }.sortedBy { it.first }
    var result = 0L
    for (a in arr) {
        val (l, r) = a
        while (pq.isNotEmpty() && pq.peek().second < l) pq.poll()
        result += pq.size
        pq.add(a)
    }
    println(result)
}