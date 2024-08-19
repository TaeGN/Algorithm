package 백준.Gold.G1.p3086_초콜릿자르기

import kotlin.math.min

const val IMPOSSIBLE = Int.MAX_VALUE

fun main() {
    val (N, M) = readln().split(" ").map(String::toInt)
    val dp = Array(N + 1) { r -> IntArray(M + 1) { c -> if (r == c) 1 else IMPOSSIBLE } }
    for (n in 1..N) {
        for (m in 1..M) {
            if (dp[n][m] != IMPOSSIBLE) continue
            for (i in 1 until n) {
                dp[n][m] = min(dp[n][m], dp[i][m] + dp[n - i][m])
            }
            for (i in 1 until m) {
                dp[n][m] = min(dp[n][m], dp[n][i] + dp[n][m - i])
            }
            if (m <= N && n <= M) dp[m][n] = dp[n][m]
        }
    }
    println(dp[N][M])
}