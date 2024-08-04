package Codeforces.Div2.Round963.A

import kotlin.math.min

fun main() = with(System.`in`.bufferedReader()) {
    val t = readLine().toInt()
    val counts = IntArray(4)
    val sb = StringBuilder()
    repeat(t) {
        counts.fill(0)
        val n = readLine().toInt()
        val word = readLine()
        for (c in word) {
            when (c) {
                '?' -> continue
                else -> counts[c - 'A']++
            }
        }
        sb.appendLine(counts.sumOf { min(n, it) })
    }
    println(sb)
}