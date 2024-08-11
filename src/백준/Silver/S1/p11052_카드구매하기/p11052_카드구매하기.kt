package 백준.Silver.S1.p11052_카드구매하기

import java.io.StreamTokenizer
import kotlin.math.max

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = nextInt()
    val dp = IntArray(N + 1)
    repeat(N) { idx ->
        val price = nextInt()
        for (i in (idx + 1)..N) {
            dp[i] = max(dp[i], dp[i - idx - 1] + price)
        }
    }
    println(dp.last())
}