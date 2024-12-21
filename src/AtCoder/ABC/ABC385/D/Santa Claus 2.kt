package AtCoder.ABC.ABC385.D

import java.util.TreeMap

data class Point(val x: Long, val y: Long)

fun main() {
    val (N, M, Sx, Sy) = readln().trim().split(" ").map(String::toInt)
    val houseList = List(N) { readln().trim().split(" ").map(String::toLong).let { Point(it[0], it[1]) } }
    val rowLine = TreeMap<Long, TreeMap<Long, Long>>()
    val colLine = TreeMap<Long, TreeMap<Long, Long>>()
    var curX = Sx.toLong()
    var curY = Sy.toLong()
    repeat(M) { _ ->
        val (D, C) = readln().trim().split(" ").let { it[0].first() to it[1].toInt() }
        when (D) {
            'U' -> {
                val x = curX
                var y1 = curY
                var y2 = curY + C
                if (x !in rowLine) rowLine[x] = TreeMap()
                while (rowLine[x]!!.floorEntry(y1)?.let { it.value >= y1 } == true) {
                    val (key, value) = rowLine[x]!!.floorEntry(y1)!!
                    rowLine[x]!!.remove(key)
                    y1 = minOf(y1, key)
                    y2 = maxOf(y2, value)
                }
                while (rowLine[x]!!.ceilingEntry(y1)?.let { it.key <= y2 } == true) {
                    val (key, value) = rowLine[x]!!.ceilingEntry(y1)!!
                    rowLine[x]!!.remove(key)
                    y1 = minOf(y1, key)
                    y2 = maxOf(y2, value)
                }
                rowLine[x]!![y1] = y2
                curY += C
            }

            'D' -> {
                val x = curX
                var y1 = curY - C
                var y2 = curY
                if (x !in rowLine) rowLine[x] = TreeMap()
                while (rowLine[x]!!.floorEntry(y1)?.let { it.value >= y1 } == true) {
                    val (key, value) = rowLine[x]!!.floorEntry(y1)!!
                    rowLine[x]!!.remove(key)
                    y1 = minOf(y1, key)
                    y2 = maxOf(y2, value)
                }
                while (rowLine[x]!!.ceilingEntry(y1)?.let { it.key <= y2 } == true) {
                    val (key, value) = rowLine[x]!!.ceilingEntry(y1)!!
                    rowLine[x]!!.remove(key)
                    y1 = minOf(y1, key)
                    y2 = maxOf(y2, value)
                }
                rowLine[x]!![y1] = y2
                curY -= C
            }

            'L' -> {
                var x1 = curX - C
                var x2 = curX
                val y = curY
                if (y !in colLine) colLine[y] = TreeMap()
                while (colLine[y]!!.floorEntry(x1)?.let { it.value >= x1 } == true) {
                    val (key, value) = colLine[y]!!.floorEntry(x1)!!
                    colLine[y]!!.remove(key)
                    x1 = minOf(x1, key)
                    x2 = maxOf(x2, value)
                }
                while (colLine[y]!!.ceilingEntry(x1)?.let { it.key <= x2 } == true) {
                    val (key, value) = colLine[y]!!.ceilingEntry(x1)!!
                    colLine[y]!!.remove(key)
                    x1 = minOf(x1, key)
                    x2 = maxOf(x2, value)
                }
                colLine[y]!![x1] = x2
                curX -= C
            }

            'R' -> {
                var x1 = curX
                var x2 = curX + C
                val y = curY
                if (y !in colLine) colLine[y] = TreeMap()
                while (colLine[y]!!.floorEntry(x1)?.let { it.value >= x1 } == true) {
                    val (key, value) = colLine[y]!!.floorEntry(x1)!!
                    colLine[y]!!.remove(key)
                    x1 = minOf(x1, key)
                    x2 = maxOf(x2, value)
                }
                while (colLine[y]!!.ceilingEntry(x1)?.let { it.key <= x2 } == true) {
                    val (key, value) = colLine[y]!!.ceilingEntry(x1)!!
                    colLine[y]!!.remove(key)
                    x1 = minOf(x1, key)
                    x2 = maxOf(x2, value)
                }
                colLine[y]!![x1] = x2
                curX += C
            }
        }
    }
    val visited = mutableSetOf<Point>()
    for ((x, y) in houseList) {
        rowLine[x]?.floorEntry(y)?.let { if (y <= it.value) visited.add(Point(x, y)) }
        colLine[y]?.floorEntry(x)?.let { if (x <= it.value) visited.add(Point(x, y)) }
    }

    println("$curX $curY ${visited.size}")
}