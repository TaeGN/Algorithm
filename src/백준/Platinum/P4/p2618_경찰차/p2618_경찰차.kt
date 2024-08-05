package 백준.Platinum.P4.p2618_경찰차

import java.io.StreamTokenizer
import kotlin.math.abs
import kotlin.math.max

const val IMPOSSIBLE = Int.MAX_VALUE shr 2

data class Point(val r: Int, val c: Int) {
    fun distance(other: Point) = abs(r - other.r) + abs(c - other.c)
}

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = nextInt()
    val W = nextInt()
    val points = mutableListOf(Point(1, 1), Point(N, N))
    repeat(W) {
        points.add(Point(nextInt(), nextInt()))
    }

    val dp = List(W + 2) { IntArray(W + 2) { IMPOSSIBLE } }
    val dpIsCarA = List(W + 2) { BooleanArray(W + 2) { false } }
    fun minDistance(p1: Int, p2: Int): Int {
        if (p1 == W + 1 || p2 == W + 1) return 0
        if (dp[p1][p2] != IMPOSSIBLE) return dp[p1][p2]
        val nextP = max(p1, p2) + 1
        val distance1 = minDistance(nextP, p2) + points[p1].distance(points[nextP])
        val distance2 = minDistance(p1, nextP) + points[p2].distance(points[nextP])
        if (distance1 < distance2) {
            dpIsCarA[p1][p2] = true
            dp[p1][p2] = distance1
        } else dp[p1][p2] = distance2
        return dp[p1][p2]
    }

    var p1 = 0
    var p2 = 1
    val sb = StringBuilder().appendLine(minDistance(p1, p2))
    for (i in 2 until dp.size) {
        if (dpIsCarA[p1][p2]) {
            sb.appendLine(1)
            p1 = i
        } else {
            sb.appendLine(2)
            p2 = i
        }
    }
    println(sb)
}