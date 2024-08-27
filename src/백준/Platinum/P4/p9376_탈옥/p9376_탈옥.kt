package 백준.Platinum.P4.p9376_탈옥

import java.util.PriorityQueue

const val IMPOSSIBLE = Int.MAX_VALUE shr 2
const val MAX_H = 100
const val MAX_W = 100
const val WALL = '*'
const val DOOR = '#'
const val TARGET = '$'
const val EMPTY = '.'
val dr = intArrayOf(1, -1, 0, 0)
val dc = intArrayOf(0, 0, 1, -1)
fun main() {
    val T = readln().toInt()
    val sb = StringBuilder()
    val matrix = Array(MAX_H + 2) { CharArray(MAX_W + 2) }
    val distMatrixArr = Array(3) { Array(MAX_H + 2) { IntArray(MAX_W + 2) } }
    repeat(T) {
        val (H, W) = readln().trim().split(" ").map(String::toInt)
        for (r in 0..(H + 1)) {
            distMatrixArr.forEach { it[r].fill(IMPOSSIBLE, 0, W + 2) }
        }
        val targetPos = mutableListOf(0 to 0)
        for (r in 1..H) {
            val input = readln().trim()
            for (c in 1..W) {
                val elm = input[c - 1]
                matrix[r][c] = elm
                if (elm == TARGET) targetPos.add(r to c)
            }
        }
        for (r in 0..(H + 1)) {
            matrix[r][0] = EMPTY
            matrix[r][W + 1] = EMPTY
        }
        for (c in 0..(W + 1)) {
            matrix[0][c] = EMPTY
            matrix[H + 1][c] = EMPTY
        }
        fun bfs(sr: Int, sc: Int, distMatrix: Array<IntArray>) {
            val pq = PriorityQueue<Triple<Int, Int, Int>>(compareBy { it.third })
            pq.add(Triple(sr, sc, 0))
            while (pq.isNotEmpty()) {
                val (r, c, count) = pq.poll()
                if (distMatrix[r][c] < count) continue
                val nCount = count + (if (matrix[r][c] == DOOR) 1 else 0)
                for (d in dr.indices) {
                    val nr = r + dr[d]
                    val nc = c + dc[d]
                    if (nr in 0..(H + 1) && nc in 0..(W + 1) && matrix[nr][nc] != WALL && distMatrix[nr][nc] > nCount) {
                        distMatrix[nr][nc] = nCount
                        pq.add(Triple(nr, nc, nCount))
                    }
                }
            }
        }

        for (i in targetPos.indices) {
            val (r, c) = targetPos[i]
            bfs(r, c, distMatrixArr[i])
        }
        var result = IMPOSSIBLE
        for (r in 0..(H + 1)) {
            for (c in 0..(W + 1)) {
                if (matrix[r][c] != WALL) result =
                    minOf(result, (if (matrix[r][c] == DOOR) 1 else 0) + distMatrixArr.sumOf { it[r][c] })
            }
        }
        sb.appendLine(result)
    }
    println(sb)
}