package AtCoder.ABC.ABC363.E

import java.io.StreamTokenizer
import java.util.PriorityQueue

val dr = intArrayOf(1, 0, -1, 0)
val dc = intArrayOf(0, -1, 0, 1)

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val H = nextInt()
    val W = nextInt()
    val Y = nextInt()
    val matrix = List(H) { IntArray(W) }
    repeat(H) { r ->
        repeat(W) { c ->
            matrix[r][c] = nextInt()
        }
    }
    val visitedMatrix = List(H) { BooleanArray(W) }
    val initSet = mutableSetOf<Pair<Int, Int>>()
    for (r in 0 until H) {
        initSet.add(r * W to matrix[r][0])
        initSet.add(r * W + W - 1 to matrix[r][W - 1])
        visitedMatrix[r][0] = true
        visitedMatrix[r][W - 1] = true
    }
    for (c in 0 until W) {
        initSet.add(c to matrix[0][c])
        initSet.add((H - 1) * W + c to matrix[H - 1][c])
        visitedMatrix[0][c] = true
        visitedMatrix[H - 1][c] = true
    }

    val pq = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })
    pq.addAll(initSet)
    val sb = StringBuilder()
    var islandCount = H * W
    for (i in 1..Y) {
        while (pq.isNotEmpty() && pq.peek().second <= i) {
            islandCount--
            val (pos, _) = pq.poll()
            val r = pos / W
            val c = pos % W

            for (d in dr.indices) {
                val nr = r + dr[d]
                val nc = c + dc[d]
                if (nr in 0 until H && nc in 0 until W && !visitedMatrix[nr][nc]) {
                    pq.add(nr * W + nc to matrix[nr][nc])
                    visitedMatrix[nr][nc] = true
                }
            }
        }
        sb.appendLine(islandCount)
    }
    println(sb)
}