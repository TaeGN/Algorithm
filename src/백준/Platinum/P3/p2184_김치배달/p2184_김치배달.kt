package 백준.Platinum.P3.p2184_김치배달

import kotlin.math.abs

const val IMPOSSIBLE = Long.MAX_VALUE shr 2

fun main() {
    val (N, L) = readln().trim().split(" ").map(String::toInt)
    val arr = IntArray(N + 1).apply { this[0] = L }
    for (i in 1..N) {
        arr[i] = readln().toInt()
    }
    arr.sort()
    val startIdx = arr.indexOf(L)
    val dp = Array(N + 1) { Array(N + 1) { LongArray(2) { IMPOSSIBLE } } }
    dp[startIdx][startIdx][0] = 0
    dp[startIdx][startIdx][1] = 0
    for (i in 1..N) {
        for (l in 0..(N - i)) {
            val r = l + i
            if (dp[l + 1][r][0] != IMPOSSIBLE) dp[l][r][0] =
                minOf(dp[l][r][0], dp[l + 1][r][0] + abs(arr[l] - arr[l + 1]).toLong() * (N - i + 1))
            if (dp[l + 1][r][1] != IMPOSSIBLE) dp[l][r][0] =
                minOf(dp[l][r][0], dp[l + 1][r][1] + abs(arr[l] - arr[r]).toLong() * (N - i + 1))
            if (dp[l][r - 1][0] != IMPOSSIBLE) dp[l][r][1] =
                minOf(dp[l][r][1], dp[l][r - 1][0] + abs(arr[r] - arr[l]).toLong() * (N - i + 1))
            if (dp[l][r - 1][1] != IMPOSSIBLE) dp[l][r][1] =
                minOf(dp[l][r][1], dp[l][r - 1][1] + abs(arr[r] - arr[r - 1]).toLong() * (N - i + 1))
        }
    }
    println(dp[0][N].min())
}