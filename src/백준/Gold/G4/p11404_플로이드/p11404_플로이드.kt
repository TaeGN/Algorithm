package 백준.Gold.G4.p11404_플로이드

import java.io.StreamTokenizer
import kotlin.math.min

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = readInt()
    val m = readInt()
    val priceMap = List(n + 1) { IntArray(n + 1) { Int.MAX_VALUE } }
    fun List<IntArray>.isNotPossible(start: Int, end: Int): Boolean = start == end || this[start][end] == Int.MAX_VALUE
    repeat(m) {
        val from = readInt()
        val to = readInt()
        val price = readInt()
        priceMap[from][to] = min(priceMap[from][to], price)
    }

    for (mid in 1..n) {
        for (start in 1..n) {
            if (priceMap.isNotPossible(start, mid)) continue
            for (end in 1..n) {
                if (priceMap.isNotPossible(mid, end)) continue
                priceMap[start][end] = min(priceMap[start][end], priceMap[start][mid] + priceMap[mid][end])
            }
        }
    }

    val sb = StringBuilder()
    for (from in 1..n) {
        for (to in 1..n) {
            val price = if (priceMap.isNotPossible(from, to)) 0 else priceMap[from][to]
            sb.append("$price ")
        }
        sb.appendLine()
    }

    println(sb)
}