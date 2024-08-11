package 백준.Silver.S3.p1788_피보나치수의확장

import kotlin.math.abs
import kotlin.math.min

const val MOD = 1_000_000_000
fun main() {
    val n = readln().toInt()
    val dp = longArrayOf(0, 1, 1)
    for (i in 3..abs(n)) {
        dp[0] = dp[1]
        dp[1] = dp[2]
        dp[2] = (dp[0] + dp[1]) % MOD
    }
    when (n) {
        0 -> println(0)
        in 1..1000000 -> println(1)
        in -1000000..-1 -> println(if (n % 2 == 0) -1 else 1)
    }
    println(dp[min(2, abs(n))])
}