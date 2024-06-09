package 백준.Silver.S3.p1004_어린왕자

import java.io.StreamTokenizer
import kotlin.math.pow

data class Point(val r: Int, val c: Int)

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val t = readInt()
    val sb = StringBuilder()
    fun Point.squareOfDistance(other: Point) =
        (this.r - other.r).toDouble().pow(2) + (this.c - other.c).toDouble().pow(2)

    repeat(t) {
        var count = 0
        val start = Point(readInt(), readInt())
        val end = Point(readInt(), readInt())
        val n = readInt()

        repeat(n) {
            val point = Point(readInt(), readInt())
            val radius = readInt()
            val squareOfRadius = radius.toDouble().pow(2)
            val startToPointSquareOfDistance = start.squareOfDistance(point)
            val endToPointSquareOfDistance = end.squareOfDistance(point)
            if (startToPointSquareOfDistance < squareOfRadius && endToPointSquareOfDistance > squareOfRadius) count++
            if (endToPointSquareOfDistance < squareOfRadius && startToPointSquareOfDistance > squareOfRadius) count++
        }

        sb.appendLine(count)
    }

    println(sb)
}