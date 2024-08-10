package 백준.Silver.S3.p2579_계단오르기

import java.io.StreamTokenizer
import kotlin.math.max

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = nextInt()
    val numArr = IntArray(N + 1)
    repeat(N) { idx ->
        numArr[idx + 1] = nextInt()
    }
    val dp = List(2) { IntArray(N + 1) }.apply { this[0][1] = numArr[1] }
    for (i in 2..N) {
        dp[0][i] = max(dp[0][i - 2], dp[1][i - 2]) + numArr[i]
        dp[1][i] = dp[0][i - 1] + numArr[i]
    }
    println(max(dp[0].last(), dp[1].last()))
}