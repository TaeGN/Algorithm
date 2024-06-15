package 백준.Silver.S1.p9465_스티커

import kotlin.math.max

const val CURRENT_IDX = 2
fun main() {
    val br = System.`in`.bufferedReader()
    val t = br.readLine().toInt()
    val sb = StringBuilder()
    repeat(t) {
        val n = br.readLine().toInt()
        val row1 = br.readLine().split(" ").map(String::toInt)
        val row2 = br.readLine().split(" ").map(String::toInt)
        val scoreLists = listOf(row1, row2)
        val dp = List(3) { intArrayOf(0, 0) }
        repeat(n) { idx ->
            for(r in 0..1) {
                dp[CURRENT_IDX - 2][r] = dp[CURRENT_IDX - 1][r]
                dp[CURRENT_IDX - 1][r] = dp[CURRENT_IDX][r]
            }
            for (r in 0..1) {
                dp[CURRENT_IDX][r] =
                    scoreLists[r][idx] + max(dp[CURRENT_IDX - 1][(r + 1) % 2], dp[CURRENT_IDX - 2][(r + 1) % 2])
            }
        }
        sb.appendLine(dp[CURRENT_IDX].max())
    }

    println(sb)
}