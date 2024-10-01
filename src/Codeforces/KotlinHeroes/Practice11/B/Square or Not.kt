package Codeforces.KotlinHeroes.Practice11.B

import kotlin.math.sqrt

fun main() {
    val sb = StringBuilder()
    fun isPossible(s: String): Boolean {
        val len = s.length
        val sqrt = sqrt(len.toDouble()).toInt()
        if (sqrt * sqrt != len) return false
        for (r in 0 until sqrt) {
            for (c in 0 until sqrt) {
                val value = if (r == 0 || r == (sqrt - 1) || c == 0 || c == (sqrt - 1)) '1' else '0'
                if (s[r * sqrt + c] != value) return false
            }
        }
        return true
    }
    repeat(readln().toInt()) {
        val N = readln().toInt()
        sb.appendLine(if (isPossible(readln())) "Yes" else "No")
    }
    println(sb)
}