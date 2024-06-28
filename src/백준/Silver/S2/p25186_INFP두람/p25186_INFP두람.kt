package 백준.Silver.S2.p25186_INFP두람

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = readInt()
    val countArr = IntArray(N)
    repeat(N) { idx ->
        countArr[idx] = readInt()
    }

    val sum = countArr.sumOf { it.toLong() }
    println(if (sum > 1 && countArr.any { it > sum / 2 }) "Unhappy" else "Happy")
}