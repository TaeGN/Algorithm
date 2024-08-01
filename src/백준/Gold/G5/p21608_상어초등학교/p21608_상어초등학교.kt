package 백준.Gold.G5.p21608_상어초등학교

import java.io.StreamTokenizer
import kotlin.math.pow

const val EMPTY = 0
val dr = intArrayOf(0, 1, 0, -1)
val dc = intArrayOf(1, 0, -1, 0)

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = nextInt()
    val matrix = List(N) { IntArray(N) }
    val friendSet = List(N * N + 1) { mutableSetOf<Int>() }
    fun isValid(r: Int, c: Int): Boolean = r in 0 until N && c in 0 until N
    repeat(N * N) {
        val num = nextInt()
        repeat(4) {
            friendSet[num].add(nextInt())
        }
        var resultR = -1
        var resultC = -1
        var maxScore = -1
        for (r in 0 until N) {
            for (c in 0 until N) {
                if (matrix[r][c] != EMPTY) continue
                var score = 0
                for (d in dr.indices) {
                    val nr = r + dr[d]
                    val nc = c + dc[d]
                    if (isValid(nr, nc)) {
                        when (matrix[nr][nc]) {
                            in friendSet[num] -> score += 10
                            EMPTY -> score += 1
                        }
                    }
                }
                if (maxScore < score) {
                    maxScore = score
                    resultR = r
                    resultC = c
                }
            }
        }
        matrix[resultR][resultC] = num
    }

    var result = 0
    for (r in 0 until N) {
        for (c in 0 until N) {
            var count = 0
            for (d in dr.indices) {
                val nr = r + dr[d]
                val nc = c + dc[d]
                if (isValid(nr, nc) && matrix[nr][nc] in friendSet[matrix[r][c]]) count++
            }
            if (count > 0) result += 10.0.pow(count - 1).toInt()
        }
    }
    println(result)
}