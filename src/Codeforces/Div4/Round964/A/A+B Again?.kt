package Codeforces.Div4.Round964.A

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val sb = StringBuilder()
    val T = nextInt()
    repeat(T) {
        sb.appendLine(nextInt().let { it / 10 + it % 10 })
    }
    println(sb)
}