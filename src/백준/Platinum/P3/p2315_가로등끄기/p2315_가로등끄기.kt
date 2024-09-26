package 백준.Platinum.P3.p2315_가로등끄기

import kotlin.math.abs

const val IMPOSSIBLE = 1_000_000_000L
fun main() {
    val (N, M) = readln().trim().split(" ").map(String::toInt)
    val arr = Array(N) { readln().trim().split(" ").map(String::toInt) }
    val wSumArr = LongArray(N)
    for (i in 0 until N) {
        wSumArr[i] = wSumArr.getOrElse(i - 1) { 0 } + arr[i][1]
    }
    fun wSum(l: Int, r: Int) = wSumArr[r] - wSumArr.getOrElse(l - 1) { 0 }
    val dp = Array(N) { Array(N) { LongArray(2) { IMPOSSIBLE } } }
    dp[M - 1][M - 1][0] = 0
    dp[M - 1][M - 1][1] = 0
    for (i in 2..N) {
        for (l in 0..(N - i)) {
            val r = l + i - 1
            dp[l][r][0] = minOf(
                dp[l][r][0],
                dp[l + 1][r][0] + abs(arr[l][0] - arr[l + 1][0]) * (wSumArr.last() - wSum(l + 1, r)),
                dp[l + 1][r][1] + abs(arr[l][0] - arr[r][0]) * (wSumArr.last() - wSum(l + 1, r))
            )
            dp[l][r][1] = minOf(
                dp[l][r][1],
                dp[l][r - 1][0] + abs(arr[r][0] - arr[l][0]) * (wSumArr.last() - wSum(l, r - 1)),
                dp[l][r - 1][1] + abs(arr[r][0] - arr[r - 1][0]) * (wSumArr.last() - wSum(l, r - 1))
            )
        }
    }
    println(dp.first().last().min())
}