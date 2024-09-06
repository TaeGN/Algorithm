package 백준.Platinum.P5.p1219_오민식의고민

const val IMPOSSIBLE = Long.MIN_VALUE shr 2
const val INF = Long.MAX_VALUE shr 2
fun main() {
    val (N, A, B, M) = readln().trim().split(" ").map(String::toInt)
    val list = mutableListOf<Triple<Int, Int, Int>>()
    repeat(M) { readln().trim().split(" ").map(String::toInt).let { list.add(Triple(it[0], it[1], it[2])) } }
    val earnArr = readln().trim().split(" ").map(String::toInt).toIntArray()
    val dp = LongArray(N) { IMPOSSIBLE }.apply { this[A] = earnArr[A].toLong() }
    repeat(2 * N) { idx ->
        for ((from, to, fee) in list) {
            if (dp[from] != IMPOSSIBLE) {
                if (dp[to] < dp[from] + earnArr[to] - fee) {
                    if (idx >= N - 1) dp[to] = INF
                    else dp[to] = dp[from] + earnArr[to] - fee
                }
            }
        }
    }
    println(
        when (dp[B]) {
            IMPOSSIBLE -> "gg"
            INF -> "Gee"
            else -> dp[B]
        }
    )
}