package Codeforces.Div4.Round964.E

import java.io.StreamTokenizer
import kotlin.math.max
import kotlin.math.min

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val pow3Arr = IntArray(13).apply { this[1] = 3 }
    for (i in 2 until pow3Arr.size) {
        pow3Arr[i] = pow3Arr[i - 1] * 3
    }

    fun count(l: Int, r: Int): Int {
        var count = (pow3Arr.binarySearch(l).let { if (it >= 0) it else -it - 2 } + 1) * 2
        for (i in 1 until pow3Arr.size) {
            if (l + 1 < pow3Arr[i]) count += (min(pow3Arr[i] - 1, r) - max(pow3Arr[i - 1], l + 1) + 1) * i
            if (r < pow3Arr[i]) break
        }
        return count
    }

    val sb = StringBuilder()
    val T = nextInt()
    repeat(T) {
        val l = nextInt()
        val r = nextInt()
        sb.appendLine(count(l, r))
    }
    println(sb)
}