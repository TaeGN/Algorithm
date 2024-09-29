package Codeforces.Div2.Round976.B

import kotlin.math.sqrt

fun main() {
    val sb = StringBuilder()
    repeat(readln().toInt()) {
        val K = readln().toLong()
        var sqrtN = sqrt(K.toDouble()).toLong()
        fun result(): Long {
            while (true) {
                if (K + sqrtN in (sqrtN * sqrtN) until ((sqrtN + 1) * (sqrtN + 1))) return K + sqrtN
                sqrtN++
            }
        }
        sb.appendLine(result())
    }
    println(sb)
}