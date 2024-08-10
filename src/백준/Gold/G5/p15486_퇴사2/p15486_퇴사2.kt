package 백준.Gold.G5.p15486_퇴사2

import java.io.StreamTokenizer
import kotlin.math.max

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = nextInt()
    val dp = IntArray(N)
    repeat(N) { idx ->
        val T = nextInt()
        val P = nextInt()
        dp[idx] = max(dp[idx], dp.getOrElse(idx - 1) { 0 })
        if (idx + T - 1 < N) dp[idx + T - 1] = max(dp[idx + T - 1], dp.getOrElse(idx - 1) { 0 } + P)
    }
    println(dp.last())
}