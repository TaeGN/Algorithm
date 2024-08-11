package Codeforces.Div2.Round965.C

import java.io.StreamTokenizer
import kotlin.math.max

class Number(var num: Int = 0, var b: Boolean = false) : Comparable<Number> {
    override fun compareTo(other: Number): Int = num.compareTo(other.num)
}

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val lowerMidArr = IntArray(200000)
    val numArr = Array(200000) { Number() }
    val sb = StringBuilder()
    val T = nextInt()
    repeat(T) {
        val n = nextInt()
        val k = nextInt()
        repeat(n) { idx -> numArr[idx].num = nextInt() }
        repeat(n) { idx -> numArr[idx].b = nextInt() == 1 }
        numArr.sort(0, n)
        var result = 0L
        for (idx in 0 until n) {
            result = max(
                result,
                numArr[idx].num.toLong() + (if (numArr[idx].b) k else 0) + (if (idx < n / 2) numArr[n / 2] else numArr[n / 2 - 1]).num
            )
        }

        var start = 1
        var end = 2000000000
        while (start < end) {
            val mid = ((1 + start.toLong() + end) / 2).toInt()
            var lowerMidArrSize = 0
            var count = 0
            for (idx in 0 until n) {
                if (numArr[idx].num >= mid) count++
                else if (numArr[idx].b) lowerMidArr[lowerMidArrSize++] = numArr[idx].num
            }
            lowerMidArr.sortDescending(0, lowerMidArrSize)
            var remainedK = k
            for (idx in 0 until lowerMidArrSize) {
                if (remainedK < mid - lowerMidArr[idx]) break
                remainedK -= mid - lowerMidArr[idx]
                count++
            }
            if (count > (n + 1) / 2) start = mid
            else end = mid - 1
        }
        result = max(result, end.toLong() + max(end, numArr[n - 1].num))
        sb.appendLine(result)
    }
    println(sb)
}