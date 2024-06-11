package 백준.Gold.G4.p1504_특정한최단경로.다익스트라

import java.io.StreamTokenizer
import java.util.PriorityQueue
import kotlin.math.min

const val IMPOSSIBLE = Int.MAX_VALUE
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = readInt()
    val distanceGraph = List(n + 1) { mutableListOf<Pair<Int, Int>>() }
    val e = readInt()
    repeat(e) {
        val pointA = readInt()
        val pointB = readInt()
        val distance = readInt()
        distanceGraph[pointA].add(pointB to distance)
        distanceGraph[pointB].add(pointA to distance)
    }

    val minDistance = IntArray(n + 1)
    fun IntArray.pair(idx: Int): Pair<Int, Int> = idx to this[idx]
    val visited = BooleanArray(n + 1)
    val pq = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })
    fun minDistance(start: Int, end: Int): Int {
        if (start == end) return 0
        minDistance.fill(IMPOSSIBLE)
        visited.fill(false)
        pq.clear()
        minDistance[start] = 0
        pq.add(minDistance.pair(start))
        while (pq.isNotEmpty()) {
            val (curIdx, curMinDistance) = pq.poll()
            if (curIdx == end) return curMinDistance
            if (visited[curIdx]) continue
            visited[curIdx] = true

            for ((nextIdx, distance) in distanceGraph[curIdx]) {
                if (minDistance[nextIdx] > curMinDistance + distance) {
                    minDistance[nextIdx] = curMinDistance + distance
                    pq.add(minDistance.pair(nextIdx))
                }
            }
        }

        return IMPOSSIBLE
    }

    fun totalMinDistance(vararg points: Int): Int {
        require(points.size > 2)
        var prevPoint = points[0]
        var totalMinDistance = 0
        for (i in 1 until points.size) {
            val curPoint = points[i]
            val distance = minDistance(prevPoint, curPoint)
            if (distance == IMPOSSIBLE) return IMPOSSIBLE
            totalMinDistance += distance
            prevPoint = curPoint
        }

        return totalMinDistance
    }

    val v1 = readInt()
    val v2 = readInt()
    val distance = min(totalMinDistance(1, v1, v2, n), totalMinDistance(1, v2, v1, n))
    println(if (distance == IMPOSSIBLE) -1 else distance)
}