package Codeforces.Div3.Round970.B

import kotlin.math.sqrt

fun main() {
    val sb = StringBuilder()
    repeat(readln().toInt()) {
        val N = readln().toInt()
        val zeroCount = readln().count { it == '0' }
        val n = sqrt(N.toDouble()).toInt()
        sb.appendLine(if (N == n * n && zeroCount == (n - 2) * (n - 2)) "Yes" else "No")
    }
    println(sb)
}