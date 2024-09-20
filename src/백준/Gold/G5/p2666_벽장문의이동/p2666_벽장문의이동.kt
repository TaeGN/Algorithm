package 백준.Gold.G5.p2666_벽장문의이동

import kotlin.math.abs

const val IMPOSSIBLE = Int.MAX_VALUE shr 2
fun main() {
    val N = readln().toInt()
    val (a, b) = readln().split(" ").map(String::toInt)
    val L = readln().toInt()
    val lIdArr = IntArray(L + 1).apply { this[0] = a }
    val rIdArr = IntArray(L + 1).apply { this[0] = b }
    repeat(L) { idx -> readln().toInt().let { lIdArr[idx + 1] = it; rIdArr[idx + 1] = it } }
    val dp = Array(L + 1) { IntArray(L + 1) { IMPOSSIBLE } }.apply { this[0][0] = 0 }
    for (i in 1..L) {
        for (j in 0 until i) {
            for (ni in 0 until i) {
                if (j != i - 1 && ni != i - 1) continue
                dp[i][j] = minOf(dp[i][j], dp[ni][j] + abs(lIdArr[i] - lIdArr[ni]))
                dp[j][i] = minOf(dp[j][i], dp[j][ni] + abs(rIdArr[i] - rIdArr[ni]))
            }
        }
    }
    var result = IMPOSSIBLE
    for (i in 0..L) {
        result = minOf(result, dp[L][i], dp[i][L])
    }
    println(result)
}