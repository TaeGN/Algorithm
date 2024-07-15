package 백준.Silver.S5.p2751_수정렬하기2

import java.io.StreamTokenizer
import java.util.PriorityQueue

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = nextInt()
    val pq = PriorityQueue<Int>(N)
    repeat(N) {
        pq.add(nextInt())
    }
    val sb = StringBuilder()
    while (pq.isNotEmpty()) sb.appendLine(pq.poll())
    println(sb)
}