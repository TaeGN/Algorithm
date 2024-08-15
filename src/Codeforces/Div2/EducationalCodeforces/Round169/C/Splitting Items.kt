package Codeforces.Div2.EducationalCodeforces.Round169.C

import java.io.StreamTokenizer
import kotlin.math.min

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val numArr = IntArray(200000)
    val sb = StringBuilder()
    val T = nextInt()
    repeat(T) {
        val n = nextInt()
        var k = nextInt()
        repeat(n) { idx -> numArr[idx] = nextInt() }
        numArr.sortDescending(0, n)
        var result = 0L
        for (i in 0 until n) {
            if (i % 2 == 0) result += numArr[i]
            else {
                result -= numArr[i] + min(k, numArr[i - 1] - numArr[i])
                k -= min(k, numArr[i - 1] - numArr[i])
            }
        }
        sb.appendLine(result)
    }
    println(sb)
}