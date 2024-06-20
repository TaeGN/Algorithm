package 백준.Silver.S4.p25214_크림파스타

import java.io.StreamTokenizer
import java.util.PriorityQueue
import kotlin.math.max

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = readInt()
    val pq = PriorityQueue<Int>(n)
    val sb = StringBuilder()
    var maxValue = 0
    repeat(n) {
        val num = readInt()
        pq.add(num)
        maxValue = max(maxValue, num - pq.peek())
        sb.append("$maxValue ")
    }

    println(sb)
}