package 백준.Gold.G3.p14220_양아치집배원

import java.io.StreamTokenizer
import kotlin.math.min

const val IMPOSSIBLE = Int.MAX_VALUE shr 2

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = nextInt()
    val matrix = Array(N) { IntArray(N) }
    repeat(N) { r -> repeat(N) { c -> matrix[r][c] = nextInt().let { if (it == 0) IMPOSSIBLE else it } } }
    val dp = Array(N) { IntArray(N) { IMPOSSIBLE } }.apply { this[0].fill(0) }
    for (count in 1 until N) {
        for (next in 0 until N) {
            for (prev in 0 until N) {
                dp[count][next] = min(dp[count][next], dp[count - 1][prev] + matrix[prev][next])
            }
        }
    }
    println(dp.last().min().let { if (it == IMPOSSIBLE) -1 else it })
}