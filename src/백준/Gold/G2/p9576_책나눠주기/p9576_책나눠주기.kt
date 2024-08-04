package 백준.Gold.G2.p9576_책나눠주기

import java.io.StreamTokenizer
import java.util.PriorityQueue

const val MAX_N = 1000
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val applicationInfoList = List(MAX_N + 1) { mutableListOf<Int>() }
    val pq = PriorityQueue<Int>()
    val sb = StringBuilder()
    val T = nextInt()
    repeat(T) {
        val N = nextInt()
        val M = nextInt()
        repeat(M) {
            applicationInfoList[nextInt()].add(nextInt())
        }
        var count = 0
        for (i in 1..N) {
            pq.addAll(applicationInfoList[i])
            applicationInfoList[i].clear()
            while (pq.isNotEmpty() && pq.peek() < i) pq.poll()
            if (pq.isNotEmpty()) {
                pq.poll()
                count++
            }
        }
        pq.clear()
        sb.appendLine(count)
    }
    println(sb)
}