package 백준.S2.p2072_오목

import java.io.BufferedReader
import java.io.InputStreamReader

enum class Color {
    EMPTY, BLACK, WHITE
}

const val MAP_SIZE = 19
val dr = intArrayOf(-1, -1, 0, 1)
val dc = intArrayOf(0, 1, 1, 1)

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt()
    val map = Array(MAP_SIZE + 1) { Array(MAP_SIZE + 1) { Color.EMPTY } }
    fun isValid(r: Int, c: Int) = r <= MAP_SIZE && c <= MAP_SIZE && r >= 1 && c >= 1
    fun isOver(r: Int, c: Int, color: Color): Boolean {
        for (d in dr.indices) {
            var count = 1
            for (w in 1..5) {
                if (isValid(r + dr[d] * w, c + dc[d] * w) && map[r + dr[d] * w][c + dc[d] * w] == color) count++
                else break
            }
            for (w in 1..5) {
                if (isValid(r - dr[d] * w, c - dc[d] * w) && map[r - dr[d] * w][c - dc[d] * w] == color) count++
                else break
            }
            if (count == 5) return true
        }
        return false
    }
    fun omok(): Int {
        for (round in 1..n) {
            val (r, c) = readLine().split(" ").map { it.toInt() }.run { Pair(this[0], this[1]) }
            val curColor = if (round % 2 == 1) Color.BLACK else Color.WHITE
            map[r][c] = curColor
            if (isOver(r, c, curColor)) return round
        }
        return -1
    }

    println(omok())
}