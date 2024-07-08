package 백준.Gold.G5.p1011_FlymetotheAlphaCentauri

import java.io.StreamTokenizer
import kotlin.math.abs
import kotlin.math.sqrt

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    fun Int.minCount(o: Int): Int =
        abs(this - o).let { diff -> sqrt(diff.toDouble()).toInt().let { it + (diff - 1) / it } }

    val T = readInt()
    val sb = StringBuilder()
    repeat(T) {
        sb.appendLine(readInt().minCount(readInt()))
    }
    println(sb)
}