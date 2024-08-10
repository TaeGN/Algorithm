package 백준.Silver.S1.p2156_포도주시식

import java.io.StreamTokenizer
import kotlin.math.max

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = nextInt()
    val dp = IntArray(3)
    repeat(n) {
        val value = nextInt()
        val zero = dp.max()
        val one = max(dp[0] + value, dp[1])
        val two = max(dp[1] + value, dp[2])
        dp[0] = zero
        dp[1] = one
        dp[2] = two
    }
    println(dp.max())
}