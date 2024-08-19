package 백준.Platinum.P3.p10803_정사각형자르기

import kotlin.math.min

const val IMPOSSIBLE = Int.MAX_VALUE
fun main() {
    val (n, m) = readln().split(" ").map(String::toInt)
    val dp = Array(n + 1) { IntArray(m + 1) { IMPOSSIBLE } }
    for (r in 1..n) {
        for (c in 1..m) {
            if (r >= c && r % c == 0) dp[r][c] = r / c
            if (r < c && c % r == 0) dp[r][c] = c / r
        }
    }
    fun dp(r: Int, c: Int): Int {
        if (dp[r][c] != IMPOSSIBLE) return dp[r][c]
        if (r >= c * 3) return 1 + dp(r - c, c)
        if (c >= r * 3) return 1 + dp(r, c - r)
        var minCount = IMPOSSIBLE
        for (i in 1..(r / 2)) {
            minCount = min(minCount, dp(i, c) + dp(r - i, c))
        }
        for (i in 1..(c / 2)) {
            minCount = min(minCount, dp(r, i) + dp(r, c - i))
        }
        return minCount.also { dp[r][c] = it }
    }
    println(dp(n, m))
}