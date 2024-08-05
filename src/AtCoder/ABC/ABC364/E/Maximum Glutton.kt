package AtCoder.ABC.ABC364.E

import java.io.StreamTokenizer
import kotlin.math.min

const val MAX_Y = 10000
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = nextInt()
    val X = nextInt()
    val Y = nextInt()
    val dp = List(N + 1) { IntArray(X + 1) { MAX_Y + 1 } }.apply { this[0][0] = 0 }
    repeat(N) { idx ->
        val A = nextInt()
        val B = nextInt()
        for (x in X downTo A) {
            for (n in 0..idx) {
                dp[n + 1][x] = min(dp[n + 1][x], dp[n][x - A] + B)
            }
        }
    }

    fun result(): Int {
        for (n in N downTo 0) {
            if (dp[n].any { it <= Y }) return min(n + 1, N)
        }
        return 1
    }
    println(result())
}