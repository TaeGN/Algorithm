package 백준.G5.p1484_다이어트

import kotlin.math.sqrt

fun main() {
    val g = System.`in`.bufferedReader().readLine().toInt()
    val sb = StringBuilder()
    for (diff in (sqrt(g.toDouble()).toInt()) downTo 1) {
        val sum = g / diff
        if (g == diff * sum && (sum + diff) % 2 == 0 && sum > diff) {
            sb.appendLine((sum + diff) / 2)
        }
    }
    if (sb.isEmpty()) sb.appendLine(-1)
    println(sb)
}