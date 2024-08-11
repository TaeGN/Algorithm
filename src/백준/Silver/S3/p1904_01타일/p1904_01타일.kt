package 백준.Silver.S3.p1904_01타일

import kotlin.math.min

fun main() {
    val N = readln().toInt()
    val dp = intArrayOf(0, 1, 2)
    for (i in 3..N) {
        dp[0] = dp[1]
        dp[1] = dp[2]
        dp[2] = (dp[1] + dp[0]) % 15746
    }
    println(dp[min(2, N)])
}