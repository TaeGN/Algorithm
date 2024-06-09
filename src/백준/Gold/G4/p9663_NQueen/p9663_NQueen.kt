package 백준.Gold.G4.p9663_NQueen

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.abs

data class Point(val r: Int, val c: Int) {
    companion object {
        lateinit var pointMap: List<List<Point>>
        fun of(r: Int = 0, c: Int = 0) = pointMap[r][c]
    }
}

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt()
    Point.pointMap = List(n) { r -> List(n) { c -> Point(r, c) } }
    val selected = MutableList(n) { Point.of() }
    println(nQueen(selected = selected))
}

fun nQueen(r: Int = 0, selected: MutableList<Point>): Int {
    if (r == selected.size) return 1

    var count = 0
    for (c in selected.indices) {
        val point = Point.of(r, c)
        if (isValid(r, c, selected)) {
            selected[r] = point
            count += nQueen(r + 1, selected)
        }
    }

    return count
}

fun isValid(r: Int, c: Int, selected: MutableList<Point>): Boolean {
    for (idx in 0 until r) {
        val point = selected[idx]
        if (c == point.c) return false

        val diffR = abs(r - point.r)
        val diffC = abs(c - point.c)
        if (diffR == diffC) return false
    }

    return true
}