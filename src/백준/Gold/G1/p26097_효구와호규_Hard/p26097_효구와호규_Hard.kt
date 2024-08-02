package 백준.Gold.G1.p26097_효구와호규_Hard

import java.io.StreamTokenizer

const val EMPTY = -1
val dr = intArrayOf(1, 0, -1, 0)
val dc = intArrayOf(0, -1, 0, 1)

data class Point(var r: Int = EMPTY, var c: Int = EMPTY) {
    fun set(r: Int, c: Int) {
        this.r = r
        this.c = c
    }

    fun setEmpty() {
        r = EMPTY
    }

    fun isEmpty() = r == EMPTY
}

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = nextInt()
    val M = nextInt()
    val matrix = List(N) { IntArray(M) }
    var zeroCount = 0
    repeat(N) { r ->
        repeat(M) { c ->
            matrix[r][c] = nextInt()
            if (matrix[r][c] == 0) zeroCount++
        }
    }

    fun isValid(r: Int, c: Int) = r in 0 until N && c in 0 until M
    fun result(): String {
        if (N * M % 2 == 1 || zeroCount % 2 == 1) return "-1"
        val sb = StringBuilder("1\n")
        val queue = ArrayDeque<Int>()
        val points = List(2) { Point() }
        var count = 0
        fun remove(r: Int, c: Int) {
            matrix[r][c] = EMPTY
            queue.add(r * M + c)
            count++
        }

        a@ for (r in 0 until N) {
            for (c in 0 until M) {
                if (r < N - 1 && matrix[r][c] == matrix[r + 1][c]) {
                    remove(r, c)
                    remove(r + 1, c)
                    sb.appendLine("${r + 1} ${c + 1} ${r + 2} ${c + 1}")
                    break@a
                }
                if (c < M - 1 && matrix[r][c] == matrix[r][c + 1]) {
                    remove(r, c)
                    remove(r, c + 1)
                    sb.appendLine("${r + 1} ${c + 1} ${r + 1} ${c + 2}")
                    break@a
                }
            }
        }
        while (queue.isNotEmpty()) {
            val (r, c) = queue.removeFirst().let { it / M to it % M }
            for (d in dr.indices) {
                val nr = r + dr[d]
                val nc = c + dc[d]
                if (isValid(nr, nc) && matrix[nr][nc] != EMPTY) {
                    val point = points[matrix[nr][nc]]
                    if (point.isEmpty()) point.set(nr, nc)
                    else if (point.r != nr || point.c != nc) {
                        val pr = point.r
                        val pc = point.c
                        point.setEmpty()
                        remove(pr, pc)
                        remove(nr, nc)
                        sb.appendLine("${pr + 1} ${pc + 1} ${nr + 1} ${nc + 1}")
                    }
                }
            }
        }

        return if (count == N * M) sb.toString() else "-1"
    }

    println(result())
}