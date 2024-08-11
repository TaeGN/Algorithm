package Codeforces.Div2.Round965.A

import java.io.StreamTokenizer
import kotlin.math.min

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val sb = StringBuilder()
    val T = nextInt()
    repeat(T) {
        val x = nextInt()
        val y = nextInt()
        val k = nextInt()
        if (k % 2 == 1) sb.appendLine("$x $y")
        val totalCount = k / 2
        val xCount = min(totalCount, min(x + 100, 100 - x))
        for (i in 1..xCount) {
            sb.appendLine("${x + i} $y")
            sb.appendLine("${x - i} $y")
        }
        val yCount = totalCount - xCount
        for (i in 1..yCount) {
            sb.appendLine("$x ${y + i}")
            sb.appendLine("$x ${y - i}")
        }
    }
    println(sb)
}