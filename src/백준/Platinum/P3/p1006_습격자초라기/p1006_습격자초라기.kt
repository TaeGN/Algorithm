package 백준.Platinum.P3.p1006_습격자초라기

import java.io.StreamTokenizer
import kotlin.math.min

const val MAX_N = 10000
const val IMPOSSIBLE = Int.MAX_VALUE shr 2

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    fun Array<IntArray>.clear(n: Int) {
        for (i in 0..n) {
            this[i].fill(IMPOSSIBLE)
        }
    }

    val dp = Array(MAX_N + 1) { IntArray(3) }
    val countArr = Array(MAX_N + 1) { IntArray(2) }.apply { this[0].fill(IMPOSSIBLE) }
    fun setDP(N: Int, W: Int) {
        for (n in 1..N) {
            dp[n][1] = min(dp[n][1], dp[n - 1][0] + 1)
            dp[n][2] = min(dp[n][2], dp[n - 1][0] + 1)
            if (countArr[n][0] + countArr[n - 1][0] <= W) dp[n][1] = min(dp[n][1], dp[n - 1][2] + 1)
            if (countArr[n][1] + countArr[n - 1][1] <= W) dp[n][2] = min(dp[n][2], dp[n - 1][1] + 1)
            if (countArr[n][0] + countArr[n][1] <= W) dp[n][0] = min(dp[n][0], dp[n - 1][0] + 1)
            if (n < N && countArr[n][0] + countArr[n + 1][0] <= W && countArr[n][1] + countArr[n + 1][1] <= W)
                dp[n + 1][0] = min(dp[n + 1][0], dp[n - 1][0] + 2)
            dp[n][0] = min(dp[n][0], min(dp[n][1], dp[n][2]) + 1)
        }
    }

    val sb = StringBuilder()
    val T = nextInt()
    repeat(T) {
        val N = nextInt()
        val W = nextInt()
        repeat(2 * N) { idx -> countArr[idx % N + 1][idx / N] = nextInt() }
        dp.clear(N)
        dp[0][0] = 0
        setDP(N, W)
        var result = dp[N][0]
        if (countArr[1][0] + countArr[N][0] <= W) {
            dp.clear(N)
            dp[1][1] = 1
            setDP(N, W)
            result = min(result, dp[N][2])
        }
        if (countArr[1][1] + countArr[N][1] <= W) {
            dp.clear(N)
            dp[1][2] = 1
            setDP(N, W)
            result = min(result, dp[N][1])
        }
        if (countArr[1][0] + countArr[N][0] <= W && countArr[1][1] + countArr[N][1] <= W) {
            dp.clear(N - 1)
            dp[1][0] = 2
            setDP(N - 1, W)
            result = min(result, dp[N - 1][0])
        }
        sb.appendLine(result)
    }
    println(sb)
}