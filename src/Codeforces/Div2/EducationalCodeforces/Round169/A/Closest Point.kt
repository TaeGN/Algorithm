package Codeforces.Div2.EducationalCodeforces.Round169.A

import java.io.StreamTokenizer
import kotlin.math.abs

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val sb = StringBuilder()
    val T = nextInt()
    val numArr = IntArray(40)
    repeat(T) {
        val n = nextInt()
        repeat(n) { idx -> numArr[idx] = nextInt() }
        sb.appendLine(if (n == 2 && abs(numArr[0] - numArr[1]) > 1) "Yes" else "No")
    }
    println(sb)
}