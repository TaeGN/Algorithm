package 백준.Gold.G1.p18128_치삼이의징검다리건너기

import java.util.PriorityQueue
import java.util.StringTokenizer
import kotlin.math.max

data class Point(val x: Int, val y: Int) {
    companion object {
        private lateinit var pointPool: List<List<Point>>
        fun isValid(x: Int, y: Int): Boolean = x in pointPool.indices && y in pointPool.indices
        fun of(x: Int, y: Int): Point = pointPool[x][y]
        fun setPointPool(n: Int) {
            pointPool = List(n) { x -> List(n) { y -> Point(x, y) } }
        }
    }
}

val dx = intArrayOf(0, 1, 1, 1, 0, -1, -1, -1)
val dy = intArrayOf(1, 1, 0, -1, -1, 1, 0, -1)
const val GROUND = 0
const val IMPOSSIBLE = Int.MAX_VALUE

fun main() = with(System.`in`.bufferedReader()) {
    var st = StringTokenizer(readLine())
    val N = st.nextToken().toInt()
    val W = st.nextToken().toInt()
    Point.setPointPool(N)
    val startPoint = Point(0, 0)
    val endPoint = Point(N - 1, N - 1)
    val map = List(N) { IntArray(N) { IMPOSSIBLE } }.apply {
        this[startPoint.x][startPoint.y] = 0
        this[endPoint.x][endPoint.y] = 0
    }
    val queue = ArrayDeque<Point>()
    repeat(W) {
        st = StringTokenizer(readLine())
        val x = st.nextToken().toInt() - 1
        val y = st.nextToken().toInt() - 1
        queue.add(Point.of(x, y))
        map[x][y] = 0
    }

    var curTime = 0
    while (queue.isNotEmpty()) {
        curTime++
        repeat(queue.size) {
            val point = queue.removeFirst()
            for (d in dx.indices step 2) {
                val x = point.x + dx[d]
                val y = point.y + dy[d]
                if (Point.isValid(x, y) && map[x][y] > curTime) {
                    queue.add(Point.of(x, y))
                    map[x][y] = curTime
                }
            }
        }
    }

    repeat(N) { x ->
        val input = readLine()
        repeat(N) { y ->
            if (input[y].digitToInt() == GROUND) map[x][y] = IMPOSSIBLE
        }
    }

    fun minArrivalTime(): Int {
        val pq = PriorityQueue<Pair<Point, Int>>(compareBy({ it.second }, { -(it.first.x + it.first.y) }))
        val visited = List(N) { BooleanArray(N) }
        pq.add(startPoint to 0)
        visited[startPoint.x][startPoint.y] = true

        while (pq.isNotEmpty()) {
            val (point, time) = pq.poll()
            if (point == endPoint) return time
            for (d in dx.indices) {
                val x = point.x + dx[d]
                val y = point.y + dy[d]
                if (Point.isValid(x, y) && map[x][y] != IMPOSSIBLE && !visited[x][y]) {
                    pq.add(Point.of(x, y) to max(time, map[x][y]))
                    visited[x][y] = true
                }
            }
        }

        return -1
    }

    println(minArrivalTime())
}