package 백준.Silver.S3.p9095_123더하기

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val dp = IntArray(11).apply { this[1] = 1; this[2] = 2; this[3] = 4 }
    for (i in 4 until dp.size) {
        dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3]
    }

    val sb = StringBuilder()
    val T = nextInt()
    repeat(T) {
        sb.appendLine(dp[nextInt()])
    }
    println(sb)
}