package 백준.Gold.G4.p24426_알고리즘수업_행렬경로문제3

import java.io.StreamTokenizer
import kotlin.math.max
import kotlin.math.min

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = readInt()
    val matrix = List(n) { IntArray(n) }
    repeat(n) { r ->
        repeat(n) { c ->
            matrix[r][c] = readInt()
        }
    }

    val startToEndDP = List(n) { IntArray(n) }
    val endToStartDP = List(n) { IntArray(n) }

    for (i in 0 until (n * 2 - 1)) {
        val startR = if (i >= n) i - (n - 1) else 0
        for (r in startR..min(i, n - 1)) {
            startToEndDP[r][i - r] = matrix[r][i - r] +
                    max(startToEndDP.getOrNull(r - 1)?.get(i - r) ?: 0, startToEndDP[r].getOrElse(i - r - 1) { 0 })
            endToStartDP[n - 1 - r][n - 1 - (i - r)] = matrix[n - 1 - r][n - 1 - (i - r)] +
                    max(endToStartDP.getOrNull(n - r)?.get(n - 1 - (i - r)) ?: 0,
                        endToStartDP[n - 1 - r].getOrElse(n - (i - r)) { 0 })
        }
    }

    val midR = readInt() - 1
    val midC = readInt() - 1
    fun maxScore(r: Int, c: Int): Int =
        if (r in 0 until n && c in 0 until n) startToEndDP[r][c] + endToStartDP[r][c] - matrix[r][c]
        else 0

    val result1 = maxScore(midR, midC)
    val result2 = (0 until n).filter { it != midR }.maxOf { maxScore(it, midC - (it -midR)) }
    println("$result1 $result2")
}