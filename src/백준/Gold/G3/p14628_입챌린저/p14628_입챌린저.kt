package 백준.Gold.G3.p14628_입챌린저

import java.util.StringTokenizer
import kotlin.math.min

const val IMPOSSIBLE = Int.MAX_VALUE shr 2

fun main() {
    var st = StringTokenizer(readln())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()
    val K = st.nextToken().toInt()
    val dp = IntArray(M + 1) { IMPOSSIBLE }.apply { this[0] = 0 }
    repeat(N) {
        st = StringTokenizer(readln())
        val X = st.nextToken().toInt()
        val Y = st.nextToken().toInt()
        for (i in (M - Y) downTo 0) {
            for (j in 1..(M - i) / Y) {
                dp[i + j * Y] = min(dp[i + j * Y], dp[i] + X * j + K * (j * (j - 1) / 2))
            }
        }
    }
    println(dp.last())
}