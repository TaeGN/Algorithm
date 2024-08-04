package Codeforces.Div2.Round963.C

import java.io.StreamTokenizer
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

const val MAX_N = 200000

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val numArr = IntArray(MAX_N)
    val sb = StringBuilder()
    val t = nextInt()
    repeat(t) {
        val n = nextInt()
        val k = nextInt()
        var maxValue = 0
        repeat(n) { idx ->
            numArr[idx] = nextInt()
            maxValue = max(maxValue, numArr[idx])
        }
        var maxDiff = Int.MIN_VALUE
        var minDiff = Int.MAX_VALUE
        repeat(n) { idx ->
            val i = (maxValue - numArr[idx]) / (2 * k)
            val diff1 = numArr[idx] - maxValue + 2 * k * i
            val diff2 = numArr[idx] - maxValue + 2 * k * (i + 1)
            val diff = if (abs(diff1) <= abs(diff2)) diff1 else diff2
            maxDiff = max(maxDiff, diff)
            minDiff = min(minDiff, diff)
        }
        sb.appendLine(if (maxDiff - minDiff < k) maxValue + maxDiff else -1)
    }
    println(sb)
}