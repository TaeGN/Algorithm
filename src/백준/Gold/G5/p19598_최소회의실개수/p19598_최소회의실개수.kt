package 백준.Gold.G5.p19598_최소회의실개수

import java.io.StreamTokenizer
import java.util.PriorityQueue

data class Time(val start: Int, val end: Int) : Comparable<Time> {
    override fun compareTo(other: Time): Int = start.compareTo(other.start)
}

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = nextInt()
    val timeList = mutableListOf<Time>()
    repeat(N) {
        timeList.add(Time(nextInt(), nextInt()))
    }
    timeList.sort()

    val pq = PriorityQueue<Int>()
    for (time in timeList) {
        if (pq.isNotEmpty() && time.start >= pq.peek()) pq.poll()
        pq.add(time.end)
    }

    println(pq.size)
}