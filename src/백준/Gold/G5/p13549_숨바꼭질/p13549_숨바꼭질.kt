package 백준.Gold.G5.p13549_숨바꼭질

import java.util.PriorityQueue

fun main() {
    val (n, k) = System.`in`.bufferedReader().readLine().split(" ").map(String::toInt).let { it[0] to it[1] }

    fun minTime(start: Int, end: Int): Int {
        if (start > end) return start - end
        val queue = PriorityQueue(compareBy<Pair<Int, Int>>({ it.second }, { it.first }))
        val visitedTimeMap = mutableMapOf<Int, Int>()
        fun PriorityQueue<Pair<Int, Int>>.checkValidationAndAdd(pos: Int, time: Int): Boolean {
            if (pos !in 0..(2 * end) || visitedTimeMap.getOrDefault(pos, Int.MAX_VALUE) <= time) return false
            this.add(pos to time)
            visitedTimeMap[pos] = time
            return true
        }

        queue.checkValidationAndAdd(start, 0)
        while (queue.isNotEmpty()) {
            val (pos, time) = queue.poll()
            if (pos == end) return time
            queue.checkValidationAndAdd(pos * 2, time)
            queue.checkValidationAndAdd(pos - 1, time + 1)
            queue.checkValidationAndAdd(pos + 1, time + 1)
        }

        return -1
    }

    println(minTime(n, k))
}