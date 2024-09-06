package 백준.Platinum.P5.p5942_BigMacsAroundTheWorld

import java.util.StringTokenizer

const val IMPOSSIBLE = Double.MAX_VALUE / 10
fun main() {
    val st = StringTokenizer(readln())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()
    val V = st.nextToken().toDouble()
    val A = st.nextToken().toInt()
    val B = st.nextToken().toInt()
    val list = mutableListOf<Triple<Int, Int, Double>>()
    repeat(M) { readln().trim().split(" ").let { list.add(Triple(it[0].toInt(), it[1].toInt(), it[2].toDouble())) } }
    val dp = DoubleArray(N + 1) { IMPOSSIBLE }.apply { this[A] = V }
    for (i in 0 until N - 1) {
        for ((from, to, rate) in list) {
            if (dp[from] != IMPOSSIBLE) dp[to] = minOf(dp[to], dp[from] * rate)
        }
    }
    println(dp[B].let { if (it == IMPOSSIBLE) 0 else it })
}