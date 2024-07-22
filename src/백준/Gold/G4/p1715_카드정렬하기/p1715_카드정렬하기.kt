package 백준.Gold.G4.p1715_카드정렬하기

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

    var result = 0
    repeat(N - 1) {
        result += (pq.poll() + pq.poll()).also { pq.add(it) }
    }
    println(result)
}