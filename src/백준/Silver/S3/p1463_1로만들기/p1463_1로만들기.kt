package 백준.Silver.S3.p1463_1로만들기

import kotlin.math.min

const val MAX_N = 1000000
fun main() {
    val dp = IntArray(MAX_N + 1) { Int.MAX_VALUE }.apply { this[1] = 0 }
    for (i in 2..MAX_N) {
        if (i % 3 == 0) dp[i] = min(dp[i], dp[i / 3] + 1)
        if (i % 2 == 0) dp[i] = min(dp[i], dp[i / 2] + 1)
        dp[i] = min(dp[i], dp[i - 1] + 1)
    }
    println(dp[readln().toInt()])
}