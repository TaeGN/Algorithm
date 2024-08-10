package 백준.Gold.G5.p2240_자두나무

import kotlin.math.max
import kotlin.math.min

fun main() {
    val (T, W) = readln().split(" ").map(String::toInt).let { it[0] to it[1] }
    val dp = List(W + 1) { IntArray(2) }.apply { this[0][1] = Int.MIN_VALUE }
    repeat(T) { idx ->
        val next = readln().toInt() - 1
        for (w in min(idx, W) downTo 0) {
            for (cur in 0..1) {
                if (cur == next) dp[w][cur]++
                else if (w < W) dp[w + 1][next] = max(dp[w + 1][next], dp[w][cur] + 1)
            }
        }
    }
    println(dp.maxOf { it.max() })
}