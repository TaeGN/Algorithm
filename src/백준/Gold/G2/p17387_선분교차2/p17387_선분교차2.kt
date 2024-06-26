package 백준.Gold.G2.p17387_선분교차2

import kotlin.math.max
import kotlin.math.min

data class Point(val x: Int, val y: Int)
data class Line(val startPoint: Point, val endPoint: Point) {
    constructor(x1: Int, y1: Int, x2: Int, y2: Int) : this(Point(x1, y1), Point(x2, y2))
}

fun main() = with(System.`in`.bufferedReader()) {
    fun ccw(p1: Point, p2: Point, p3: Point): Int {
        val ccw = (p1.x.toLong() * p2.y + p2.x.toLong() * p3.y + p3.x.toLong() * p1.y) -
                (p2.x.toLong() * p1.y + p3.x.toLong() * p2.y + p1.x.toLong() * p3.y)
        return when {
            ccw > 0 -> 1
            ccw < 0 -> -1
            else -> 0
        }
    }

    fun Line.ccw(other: Line): Int =
        ccw(startPoint, endPoint, other.startPoint) * ccw(startPoint, endPoint, other.endPoint)

    fun Line.isCross(other: Line): Boolean {
        val ccw1 = ccw(other)
        val ccw2 = other.ccw(this)
        return if (ccw1 == 0 && ccw2 == 0) min(startPoint.x, endPoint.x) <= max(other.startPoint.x, other.endPoint.x)
                && min(other.startPoint.x, other.endPoint.x) <= max(startPoint.x, endPoint.x)
                && min(startPoint.y, endPoint.y) <= max(other.startPoint.y, other.endPoint.y)
                && min(other.startPoint.y, other.endPoint.y) <= max(startPoint.y, endPoint.y)
        else ccw1 <= 0 && ccw2 <= 0
    }

    val line1 = readLine().split(" ").map(String::toInt).let { Line(it[0], it[1], it[2], it[3]) }
    val line2 = readLine().split(" ").map(String::toInt).let { Line(it[0], it[1], it[2], it[3]) }
    println(if (line1.isCross(line2)) 1 else 0)
}