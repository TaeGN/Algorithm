package 백준.Silver.S1.p2302_극장좌석

import java.io.StreamTokenizer

const val MAX_N = 40

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val dp = IntArray(MAX_N + 1).apply { this[0] = 1; this[1] = 1 }
    for (i in 2..MAX_N) {
        dp[i] = dp[i - 1] + dp[i - 2]
    }

    val N = nextInt()
    val M = nextInt()
    val numArr = IntArray(M + 1).apply { this[M] = N + 1 }
    repeat(M) { idx -> numArr[idx] = nextInt() }
    numArr.sort()
    var result = 1
    var prev = 0
    for (num in numArr) {
        result *= dp[num - prev - 1]
        prev = num
    }
    println(result)
}