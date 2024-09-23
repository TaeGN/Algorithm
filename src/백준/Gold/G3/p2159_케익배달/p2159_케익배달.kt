package 백준.Gold.G3.p2159_케익배달

import kotlin.math.abs

val dr = intArrayOf(0, 0, 0, 1, -1)
val dc = intArrayOf(0, 1, -1, 0, 0)
const val IMPOSSIBLE = Long.MAX_VALUE shr 2
fun main() {
    val N = readln().toInt()
    val pos = Array(N + 1) { readln().trim().split(" ").map(String::toInt) }
    val dp = Array(N + 1) { LongArray(5) { IMPOSSIBLE } }.apply { this[0][0] = 0 }
    for (i in 1..N) {
        val prev = pos[i - 1]
        val cur = pos[i]
        for (pd in dr.indices) {
            val pr = prev[0] + dr[pd]
            val pc = prev[1] + dc[pd]
            for (cd in dr.indices) {
                val cr = cur[0] + dr[cd]
                val cc = cur[1] + dc[cd]
                dp[i][cd] = minOf(dp[i][cd], dp[i - 1][pd] + abs(pr - cr) + abs(pc - cc))
            }
        }
    }
    println(dp[N].min())
}