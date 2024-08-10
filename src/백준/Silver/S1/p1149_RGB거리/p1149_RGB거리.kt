package 백준.Silver.S1.p1149_RGB거리

import java.io.StreamTokenizer
import kotlin.math.min

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = nextInt()
    val dp = List(N + 1) { IntArray(3) }
    repeat(N) { i ->
        repeat(3) { j ->
            dp[i + 1][j] = nextInt() + min(dp[i][(j + 1) % 3], dp[i][(j + 2) % 3])
        }
    }
    println(dp.last().min())
}