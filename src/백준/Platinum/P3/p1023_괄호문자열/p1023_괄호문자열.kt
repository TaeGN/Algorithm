package 백준.Platinum.P3.p1023_괄호문자열

const val EMPTY = -1L
const val IMPOSSIBLE = Int.MIN_VALUE shr 2
fun main() {
    val (N, K) = readln().trim().split(" ").let { it[0].toInt() to it[1].toLong() }
    val dp = Array(N + 1) { LongArray(N + 1) { EMPTY } }
    fun dp(n: Int = N, state: Int = 0): Long {
        if (n == 0) return if (state == 0) 1 else 0
        if (dp[n][state] != EMPTY) return dp[n][state]
        dp[n][state] = dp(n - 1, state + 1) + (if (state > 0) dp(n - 1, state - 1) else 0)
        return dp[n][state]
    }

    fun result(n: Int = N, state: Int = 0, k: Long = K): String {
        if (n == 0) return ""
        val count = (1L shl (n - 1)) - (if (state == IMPOSSIBLE) 0 else dp(n - 1, state + 1))
        return if (k < count) "(" + result(n - 1, if (state == IMPOSSIBLE) IMPOSSIBLE else state + 1, k)
        else ")" + result(n - 1, if (state == 0 || state == IMPOSSIBLE) IMPOSSIBLE else state - 1, k - count)
    }
    if (K < (1L shl N) - dp()) println(result())
    else println(-1)
}