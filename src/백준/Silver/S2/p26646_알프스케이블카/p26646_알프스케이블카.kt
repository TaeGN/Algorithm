package 백준.Silver.S2.p26646_알프스케이블카

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    fun Int.pow2(): Long = this.toLong() * this
    val n = readInt()
    var minPrice = 0L
    repeat(n) { idx ->
        minPrice += readInt().pow2() * if (idx in 1 until n - 1) 4 else 2
    }
    println(minPrice)
}