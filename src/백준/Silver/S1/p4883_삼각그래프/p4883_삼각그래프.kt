package 백준.Silver.S1.p4883_삼각그래프

import java.io.StreamTokenizer
import kotlin.math.min

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val dp = IntArray(3)
    val sb = StringBuilder()
    var N = nextInt()
    var count = 0
    while (N != 0) {
        nextInt()
        dp[0] = Int.MAX_VALUE
        dp[1] = nextInt()
        dp[2] = dp[1] + nextInt()
        repeat(N - 1) {
            val nextZero = min(dp[0], dp[1]) + nextInt()
            val nextOne = min(nextZero, min(dp[0], min(dp[1], dp[2]))) + nextInt()
            val nextTwo = min(nextOne, min(dp[1], dp[2])) + nextInt()
            dp[0] = nextZero
            dp[1] = nextOne
            dp[2] = nextTwo
        }
        sb.appendLine("${++count}. ${dp[1]}")
        N = nextInt()
    }
    println(sb)
}