package 백준.Silver.S2.p1699_제곱수의합

import kotlin.math.min
import kotlin.math.sqrt

const val IMPOSSIBLE = Int.MAX_VALUE
fun main() {
    val N = readln().toInt()
    val dp = IntArray(N + 1) { IMPOSSIBLE }.apply { this[0] = 0 }
    for (i in 1..sqrt(N.toDouble()).toInt()) {
        val num = i * i
        for (j in num..N) {
            dp[j] = min(dp[j], dp[j - num] + 1)
        }
    }
    println(dp[N])
}