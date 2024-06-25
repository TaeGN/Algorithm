package 백준.Gold.G5.p2166_다각형의면적

import java.io.StreamTokenizer
import kotlin.math.abs

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    fun triangleArea(x1: Int, y1: Int, x2: Int, y2: Int, x3: Int, y3: Int): Double =
        ((x1.toLong() * y2 + x2.toLong() * y3 + x3.toLong() * y1) -
                (x2.toLong() * y1 + x3.toLong() * y2 + x1.toLong() * y3)).toDouble() / 2

    val N = readInt()
    val x1 = readInt()
    val y1 = readInt()
    var x2 = readInt()
    var y2 = readInt()
    var totalArea = 0.0
    repeat(N - 2) {
        val x3 = readInt()
        val y3 = readInt()
        totalArea += triangleArea(x1, y1, x2, y2, x3, y3)
        x2 = x3
        y2 = y3
    }
    println(String.format("%.1f", abs(totalArea)))
}