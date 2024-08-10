package 백준.Silver.S2.p1912_연속합

import java.io.StreamTokenizer
import kotlin.math.max

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = nextInt()
    val dp = IntArray(n)
    repeat(n) { idx ->
        dp[idx] = nextInt() + max(0, dp.getOrElse(idx - 1) { 0 })
    }
    println(dp.max())
}