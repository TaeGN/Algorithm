package AtCoder.ABC.ABC358.C

import kotlin.math.min

fun main() {
    val (N, M) = readln().split(" ").map(String::toInt)
    val dp = IntArray(1 shl M) { Int.MAX_VALUE shr 2 }.apply { this[0] = 0 }
    repeat(N) {
        val input = readln()
        for (i in dp.indices) {
            var nextI = i
            for (j in 0 until M) {
                if (input[j] == 'o') nextI = nextI or (1 shl j)
            }
            dp[nextI] = min(dp[nextI], dp[i] + 1)
        }
    }
    println(dp.last())
}