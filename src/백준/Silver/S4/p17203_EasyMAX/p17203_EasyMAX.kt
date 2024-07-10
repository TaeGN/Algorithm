package 백준.Silver.S4.p17203_EasyMAX

import java.io.StreamTokenizer
import kotlin.math.abs
import kotlin.math.max

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = nextInt()
    val Q = nextInt()
    val dSum = IntArray(N + 1)
    var prev = nextInt()
    repeat(N - 1) { idx ->
        val cur = nextInt()
        val diff = abs(cur - prev)
        dSum[idx + 2] = dSum[idx + 1] + diff
        prev = cur
    }

    val sb = StringBuilder()
    repeat(Q) {
        sb.appendLine(max(0, -dSum[nextInt()] + dSum[nextInt()]))
    }
    println(sb)
}