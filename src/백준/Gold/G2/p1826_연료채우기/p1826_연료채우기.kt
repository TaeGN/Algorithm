package 백준.Gold.G2.p1826_연료채우기

import java.util.PriorityQueue

fun main() {
    val N = readln().toInt()
    val list = Array(N) { readln().split(" ").map(String::toInt) }.sortedBy { it[0] }
    val pq = PriorityQueue<Int>(Comparator.reverseOrder())
    val (L, P) = readln().split(" ").map(String::toInt)
    var fuel = P
    var count = 0
    var listIdx = 0
    while (fuel < L) {
        while (listIdx < N && list[listIdx][0] <= fuel) pq.add(list[listIdx++][1])
        if (pq.isEmpty()) break
        fuel += pq.poll()
        count++
    }
    println(if (fuel < L) -1 else count)
}