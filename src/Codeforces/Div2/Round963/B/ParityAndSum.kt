package Codeforces.Div2.Round963.B

import java.io.StreamTokenizer
import kotlin.math.max
import kotlin.math.min

const val MAX_N = 200000
const val MAX_A = 1_000_000_000

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val sb = StringBuilder()
    val numArr = IntArray(MAX_N)
    val t = nextInt()
    repeat(t) {
        val n = nextInt()
        var evenCount = 0
        var maxOdd = 0
        var maxEven = 0
        repeat(n) { idx ->
            numArr[idx] = nextInt()
            if (numArr[idx] % 2 == 0) {
                evenCount++
                maxEven = max(maxEven, numArr[idx])
            } else maxOdd = max(maxOdd, numArr[idx])
        }
        numArr.sort(0, n)
        if (evenCount == n) sb.appendLine(0)
        else {
            var count = evenCount
            for (idx in 0 until n) {
                if (numArr[idx] % 2 == 0) {
                    if (numArr[idx] > maxOdd) {
                        count++
                        break
                    } else {
                        maxOdd = min(MAX_A, maxOdd + numArr[idx])
                    }
                }
            }
            sb.appendLine(count)
        }
    }
    println(sb)
}