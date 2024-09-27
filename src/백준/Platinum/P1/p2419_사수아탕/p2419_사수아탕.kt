package 백준.Platinum.P1.p2419_사수아탕

import kotlin.math.abs

const val EMPTY = -1
fun main() {
    val (N, M) = readln().trim().split(" ").map(String::toInt)
    val xArr = IntArray(N + 1)
    for (i in 1..N) {
        xArr[i] = readln().toInt()
    }
    xArr.sort()
    val S = xArr.indexOf(0)
    val dp = Array(N + 1) { Array(N + 1) { Array(2) { EMPTY } } }
    fun dp(count: Int, l: Int = S, r: Int = S, pos: Int = 0): Int {
        if (count == 0) return 0
        if (dp[l][r][pos] != EMPTY) return dp[l][r][pos]
        val cur = if (pos == 0) l else r
        dp[l][r][pos] = 0
        if (l > 0) dp[l][r][pos] =
            maxOf(dp[l][r][pos], dp(count - 1, l - 1, r, 0) + M - count * abs(xArr[cur] - xArr[l - 1]))
        if (r < N) dp[l][r][pos] =
            maxOf(dp[l][r][pos], dp(count - 1, l, r + 1, 1) + M - count * abs(xArr[cur] - xArr[r + 1]))
        return dp[l][r][pos]
    }

    var result = 0
    for (i in 1..N) {
        dp.forEach { r -> r.forEach { c -> c.fill(EMPTY) } }
        result = maxOf(result, dp(i))
    }
    println(result)
}