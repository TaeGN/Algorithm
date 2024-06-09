package 백준.Gold.G1.p1799_비숍

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer
import kotlin.math.abs
import kotlin.math.max

data class Point(val r: Int, val c: Int) {
    companion object {
        val Empty = Point(-1, -1)
        lateinit var pointPool: List<List<Point>>
        fun of(r: Int = 0, c: Int = 0) = pointPool[r][c]
    }

    fun isNotEmpty() = this != Empty
}

class Chess(val map: List<List<Boolean>>) {
    private val bishopMap: List<List<Point>>
    private var maxBishopCount = 0
    init {
        fun bishopPoints(idx: Int): List<Point> {
            val list = mutableListOf<Point>()
            repeat(map.size - abs(idx)) { i ->
                val point = if (idx >= 0) Point.of(i, i + idx) else Point.of(i - idx, i)
                if (map[point.r][point.c]) list.add(point)
            }
            return list
        }

        val maxIdx = map.size - 1
        bishopMap = (-maxIdx..maxIdx).map(::bishopPoints)
    }

    fun maxBishopCount(): Int {
        makeMaxBishopCount()
        return maxBishopCount
    }

    private fun makeMaxBishopCount(idx: Int = 0, count: Int = 0, selected: Array<Point> = Array(map.size * 2 - 1) { Point.of() }) {
        if(bishopMap.size - idx + count <= maxBishopCount) return
        if(idx == bishopMap.size) {
            maxBishopCount = max(maxBishopCount, count)
            return
        }
        for (point in bishopMap[idx]) {
            selected[idx] = point
            if (checkDiagonal(idx, selected)) makeMaxBishopCount(idx + 1, count + 1, selected)
        }
        selected[idx] = Point.Empty
        makeMaxBishopCount(idx + 1, count, selected)
    }

    private fun checkDiagonal(idx: Int, selected: Array<Point>): Boolean {
        val curPoint = selected[idx]
        repeat(idx - 1) { prevIdx ->
            val prevPoint = selected[prevIdx]
            if (prevPoint.isNotEmpty() &&
                abs(curPoint.r - prevPoint.r) == abs(curPoint.c - prevPoint.c)) return false
        }
        return true
    }
}


fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val size = readLine().toInt()
    val map = List(size) { MutableList(size) { false } }
    Point.pointPool = List(size) { r -> List(size) { c -> Point(r, c) } }
    repeat(size) { r ->
        val st = StringTokenizer(readLine())
        repeat(size) { c ->
            map[r][c] = when (st.nextToken()) {
                "1" -> true
                "0" -> false
                else -> throw IllegalArgumentException()
            }
        }
    }

    val chess = Chess(map)
    println(chess.maxBishopCount())
}