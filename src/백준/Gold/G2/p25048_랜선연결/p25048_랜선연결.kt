package 백준.Gold.G2.p25048_랜선연결

import java.io.StreamTokenizer
import kotlin.math.min

const val IMPOSSIBLE = Long.MAX_VALUE shr 2

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = nextInt()
    val aArr = IntArray(N + 1)
    val bArr = IntArray(N + 1)
    repeat(N) { idx ->
        aArr[idx + 1] = nextInt() - 2
        bArr[idx + 1] = nextInt()
    }

    val M = nextInt()
    val dp = LongArray(M) { IMPOSSIBLE }.apply { this[0] = 0 }
    for (i in 1..N) {
        for (m in (M - 1) downTo aArr[i]) {
            dp[m] = min(dp[m], dp[m - aArr[i]] + bArr[i])
        }
    }
    println(if (dp[M - 1] == IMPOSSIBLE) -1 else dp[M - 1])
}