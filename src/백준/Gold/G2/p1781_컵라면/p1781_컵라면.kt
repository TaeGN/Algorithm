package 백준.Gold.G2.p1781_컵라면

import java.util.PriorityQueue

fun main() {
    val N = readln().toInt()
    val list = List(N) { readln().split(" ").map(String::toInt) }.sortedBy { -it[0] }
    val pq = PriorityQueue<Int>(Comparator.reverseOrder())
    var idx = 0
    var result = 0
    for (time in N downTo 1) {
        while (idx < N && list[idx][0] >= time) pq.add(list[idx++][1])
        if (pq.isNotEmpty()) result += pq.poll()
    }
    println(result)
}