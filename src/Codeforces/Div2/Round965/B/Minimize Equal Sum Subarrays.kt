package Codeforces.Div2.Round965.B

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val sb = StringBuilder()
    val T = nextInt()
    repeat(T) {
        val n = nextInt()
        val first = nextInt()
        repeat(n - 1) {
            sb.append("${nextInt()} ")
        }
        sb.appendLine(first)
    }
    println(sb)
}