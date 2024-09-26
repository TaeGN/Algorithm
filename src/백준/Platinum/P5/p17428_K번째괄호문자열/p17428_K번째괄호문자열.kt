package 백준.Platinum.P5.p17428_K번째괄호문자열

const val EMPTY = -1L
const val IMPOSSIBLE = Int.MIN_VALUE shr 2
fun main() {
    val (N, K) = readln().trim().split(" ").let { it[0].toInt() to it[1].toLong() }
    val dp = Array(N + 1) { LongArray(N + 1) { EMPTY } }
    fun dp(len: Int = N, state: Int = 0): Long {
        if (len == 0) return if (state == 0) 1 else 0
        if (dp[len][state] != EMPTY) return dp[len][state]
        dp[len][state] = dp(len - 1, state + 1) + (if (state > 0) dp(len - 1, state - 1) else 0)
        return dp[len][state]
    }

    fun result(len: Int = N, state: Int = 0, k: Long = K): String {
        if (len == 0) return ""
        val count = if (state == IMPOSSIBLE) 0 else dp(len - 1, state + 1)
        return if (k < count) "(" + result(len - 1, if (state == IMPOSSIBLE) IMPOSSIBLE else state + 1, k)
        else ")" + result(len - 1, if (state == 0 || state == IMPOSSIBLE) IMPOSSIBLE else state - 1, k - count)
    }
    if (K < dp()) println(result())
    else println(-1)
}