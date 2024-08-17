package Codeforces.Div2.EducationalCodeforces.Round169.C

import kotlin.math.min

fun main() {
    val sb = StringBuilder()
    val T = readln().toInt()
    repeat(T) {
        val (n, k) = readln().split(" ").map(String::toInt)
        val numArr = readln().split(" ").map(String::toInt).sortedDescending().toIntArray()
        var result = 0L
        var remainedK = k
        for (i in 0 until n) {
            if (i % 2 == 0) result += numArr[i]
            else {
                result -= numArr[i] + min(remainedK, numArr[i - 1] - numArr[i])
                remainedK -= min(remainedK, numArr[i - 1] - numArr[i])
            }
        }
        sb.appendLine(result)
    }
    println(sb)
}