package 백준.Gold.G3.p2457_공주님의정원

import java.util.PriorityQueue

fun main() {
    val days = intArrayOf(0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
    val daysSum = IntArray(13)
    for (i in 1..12) {
        daysSum[i] = daysSum[i - 1] + days[i]
    }
    val N = readln().toInt()
    val arr =
        Array(N) {
            readln().split(" ").map(String::toInt).let { (daysSum[it[0] - 1] + it[1]) to (daysSum[it[2] - 1] + it[3]) }
        }
            .sortedBy { it.first }
    val start = daysSum[2] + 1
    val end = daysSum[10] + 30
    var cur = start
    val pq = PriorityQueue<Int>(Comparator.reverseOrder())
    var idx = 0
    var result = 0
    while (cur <= end) {
        while (idx < N && arr[idx].first <= cur) pq.add(arr[idx++].second)
        if (pq.isEmpty() || cur >= pq.peek()) break
        cur = pq.poll()
        result++
    }
    println(if (cur <= end) 0 else result)
}