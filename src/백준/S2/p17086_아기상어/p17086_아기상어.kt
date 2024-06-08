package 백준.S2.p17086_아기상어

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

const val BABY_SHARK = 1
data class Point(val r: Int, val c: Int)
operator fun Point.minus(other: Point): Int {
    return max(abs(r - other.r), abs(c - other.c))
}

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    var st = StringTokenizer(readLine())
    val rLen = st.nextToken().toInt()
    val cLen = st.nextToken().toInt()

    val distanceMap = mutableMapOf<Point, Int>()
    val babySharks = mutableListOf<Point>()
    for (r in 0 until rLen) {
        st = StringTokenizer(readLine())
        for (c in 0 until cLen) {
            val point = Point(r, c)
            distanceMap[point] = Int.MAX_VALUE
            if (st.nextToken().toInt() == BABY_SHARK) {
                babySharks.add(point)
            }
        }
    }

    fun query(babyShark: Point) {
        for ((point, distance) in distanceMap) {
            distanceMap[point] = min(distance, babyShark - point)
        }
    }

    babySharks.forEach { query(it) }
    println(distanceMap.values.max())
}