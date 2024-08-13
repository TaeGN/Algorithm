package 백준.Silver.S2.p1965_상자넣기

import java.io.StreamTokenizer
import kotlin.math.max

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = nextInt()
    val dp = IntArray(1001)
    repeat(n) {
        val num = nextInt()
        for (i in 0 until num) {
            dp[num] = max(dp[num], dp[i] + 1)
        }
    }
    println(dp.max())
}