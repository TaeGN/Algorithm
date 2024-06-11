package 백준.Gold.G4.p1504_특정한최단경로.플로이드워셜

import java.io.StreamTokenizer
import kotlin.math.min

const val IMPOSSIBLE = Int.MAX_VALUE
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = readInt()
    val distanceGraph = List(n + 1) { r -> IntArray(n + 1) { c -> if (r == c) 0 else IMPOSSIBLE } }
    fun List<IntArray>.isNotValid(pointA: Int, pointB: Int): Boolean =
        pointA == pointB || this[pointA][pointB] == IMPOSSIBLE

    fun List<IntArray>.totalDistance(vararg points: Int): Int {
        require(points.size > 2)
        var prevPoint = points[0]
        var totalDistance = 0
        for (i in 1 until points.size) {
            val curPoint = points[i]
            val distance = this[prevPoint][curPoint]
            if (distance == IMPOSSIBLE) return IMPOSSIBLE
            totalDistance += distance
            prevPoint = curPoint
        }

        return totalDistance
    }

    val e = readInt()
    repeat(e) {
        val pointA = readInt()
        val pointB = readInt()
        val distance = readInt()
        distanceGraph[pointA][pointB] = distance
        distanceGraph[pointB][pointA] = distance
    }

    for (mid in 1..n) {
        for (start in 1..n) {
            if (distanceGraph.isNotValid(start, mid)) continue
            for (end in 1..n) {
                if (distanceGraph.isNotValid(mid, end)) continue
                distanceGraph[start][end] =
                    min(distanceGraph[start][end], distanceGraph[start][mid] + distanceGraph[mid][end])
            }
        }
    }

    val v1 = readInt()
    val v2 = readInt()
    val distance = min(distanceGraph.totalDistance(1, v1, v2, n), distanceGraph.totalDistance(1, v2, v1, n))
    println(if (distance == IMPOSSIBLE) -1 else distance)
}