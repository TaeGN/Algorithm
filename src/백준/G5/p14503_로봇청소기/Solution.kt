package 백준.G5.p14503_로봇청소기

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

const val NORTH = 0
const val EAST = 1
const val SOUTH = 2
const val WEST = 3
const val CLEAN = 1
const val NOT_CLEAN = 0
val dr = intArrayOf(-1, 0, 1, 0)
val dc = intArrayOf(0, 1, 0, -1)
lateinit var mapSize: Point
lateinit var curPoint: Point
lateinit var curDirection: Direction
lateinit var map: Array<IntArray>

class Point(val r: Int, val c: Int)
class Direction(val d: Int) {
    fun rotateCCW90(): Direction {
        return Direction(when (d) {
            NORTH -> WEST
            WEST -> SOUTH
            SOUTH -> EAST
            EAST -> NORTH
            else -> error("error")
        })
    }
}

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    var st = StringTokenizer(readLine())
    mapSize = Point(st.nextToken().toInt(), st.nextToken().toInt())

    st = StringTokenizer(readLine())
    curPoint = Point(st.nextToken().toInt(), st.nextToken().toInt())
    curDirection = Direction(st.nextToken().toInt())

    map = Array<IntArray>(mapSize.r) {
        st = StringTokenizer(readLine())
        IntArray(mapSize.c) { st.nextToken().toInt() }
    }

    println(queryAll())
}

fun queryAll(): Int {
    var count = 0
    do {
        if (map[curPoint.r][curPoint.c] == NOT_CLEAN) {
            map[curPoint.r][curPoint.c] = CLEAN
            count++
        }
    } while (query())
    return count
}

fun query(): Boolean {
    for (d in dr.indices) {
        val aroundR = curPoint.r + dr[d]
        val aroundC = curPoint.c + dc[d]
        if (isNotValid(aroundR, aroundC)) continue
        if (map[aroundR][aroundC] == NOT_CLEAN) {
            curDirection = curDirection.rotateCCW90()
            val nextR = curPoint.r + dr[curDirection.d]
            val nextC = curPoint.c + dc[curDirection.d]
            if (isValid(nextR, nextC) && map[nextR][nextC] == NOT_CLEAN) {
                curPoint = Point(nextR, nextC)
            }
            return true
        }
    }

    val nextR = curPoint.r - dr[curDirection.d]
    val nextC = curPoint.c - dc[curDirection.d]
    if (isValid(nextR, nextC)) {
        curPoint = Point(nextR, nextC)
        return true
    }

    return false
}

fun isNotValid(r: Int, c: Int): Boolean {
    return !isValid(r, c)
}

fun isValid(r: Int, c: Int): Boolean {
    return r >= 0 && c >= 0 && r < mapSize.r && c < mapSize.c
}