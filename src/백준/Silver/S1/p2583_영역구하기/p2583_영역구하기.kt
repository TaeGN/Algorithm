package 백준.Silver.S1.p2583_영역구하기

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val m = readInt()
    val n = readInt()
    val k = readInt()
    val map = List(m) { BooleanArray(n) }
    fun List<BooleanArray>.isValid(r: Int, c: Int): Boolean = try {
        this[r][c]
        true
    } catch (e: IndexOutOfBoundsException) {
        false
    }

    repeat(k) {
        val startC = readInt()
        val startR = readInt()
        val endC = readInt()
        val endR = readInt()
        for (r in startR until endR) {
            map[r].fill(true, startC, endC)
        }
    }

    val dr = intArrayOf(0, 1, 0, -1)
    val dc = intArrayOf(1, 0, -1, 0)
    fun area(r: Int, c: Int, visited: List<BooleanArray>): Int {
        val queue = ArrayDeque<Pair<Int, Int>>()
        visited[r][c] = true
        queue.add(r to c)
        var count = 0

        while (queue.isNotEmpty()) {
            val (curR, curC) = queue.removeFirst()
            count++

            for (d in dr.indices) {
                val nextR = curR + dr[d]
                val nextC = curC + dc[d]
                if (map.isValid(nextR, nextC) && !visited[nextR][nextC]) {
                    visited[nextR][nextC] = true
                    queue.add(nextR to nextC)
                }
            }
        }

        return count
    }

    val visited = map.map { it.copyOf() }
    val areaSizeList = mutableListOf<Int>()
    var count = 0
    for (r in 0 until m) {
        for (c in 0 until n) {
            if (visited[r][c]) continue
            areaSizeList.add(area(r, c, visited))
            count++
        }
    }
    areaSizeList.sort()

    println(count)
    println(areaSizeList.joinToString(separator = " "))
}