package AtCoder.ABC.ABC358.G

import kotlin.math.max
import kotlin.math.min

val dr = intArrayOf(1, 0, -1, 0)
val dc = intArrayOf(0, -1, 0, 1)
const val IMPOSSIBLE = Long.MIN_VALUE

fun main() {
    val (H, W, K) = readln().split(" ").map(String::toInt)
    val (sr, sc) = readln().split(" ").map { it.toInt() - 1 }
    val matrix = Array(H) { readln().split(" ").map(String::toInt) }
    val dp = Array(H * W) { Array(H) { LongArray(W) { IMPOSSIBLE } } }.apply { this[0][sr][sc] = 0 }
    for (i in 1 until dp.size) {
        for (r in 0 until H) {
            for (c in 0 until W) {
                for (d in dr.indices) {
                    val pr = r + dr[d]
                    val pc = c + dc[d]
                    if (pr in 0 until H && pc in 0 until W && dp[i - 1][pr][pc] != IMPOSSIBLE) {
                        dp[i][r][c] = max(dp[i][r][c], dp[i - 1][pr][pc] + matrix[r][c])
                    }
                }
            }
        }
    }

    var result = 0L
    for (i in 0..min(dp.size - 1, K)) {
        for (r in 0 until H) {
            for (c in 0 until W) {
                result = max(result, dp[i][r][c] + (K - i).toLong() * matrix[r][c])
            }
        }
    }
    println(result)
}