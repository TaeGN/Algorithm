package AtCoder.ABC.ABC365.D

import kotlin.math.max

fun main() {
    val N = readln().toInt()
    val dp = List(2) { IntArray(3) }
    for (c in readln()) {
        val idx = when (c) {
            'R' -> 0
            'P' -> 1
            'S' -> 2
            else -> throw IllegalArgumentException()
        }
        dp[1][(idx + 2) % 3] = 0
        dp[1][idx] = max(dp[0][(idx + 1) % 3], dp[0][(idx + 2) % 3])
        dp[1][(idx + 1) % 3] = 1 + max(dp[0][idx], dp[0][(idx + 2) % 3])
        for (i in 0 until 3) {
            dp[0][i] = dp[1][i]
        }
    }
    println(dp[1].max())
}