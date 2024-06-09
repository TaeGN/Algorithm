package 백준.Gold.G3.p15683_감시

import java.io.StreamTokenizer
import kotlin.math.min

const val WALL = -1

enum class Direction(val dr: Int, val dc: Int) {
    NORTH(-1, 0) {
        override fun next(): Direction = EAST
    },
    EAST(0, 1) {
        override fun next(): Direction = SOUTH
    },
    SOUTH(1, 0) {
        override fun next(): Direction = WEST
    },
    WEST(0, -1) {
        override fun next(): Direction = NORTH
    };

    abstract fun next(): Direction
}

class CCTV(val r: Int, val c: Int, val id: Int) {
    private val directions = when (id) {
        1 -> listOf(Direction.NORTH)
        2 -> listOf(Direction.NORTH, Direction.SOUTH)
        3 -> listOf(Direction.NORTH, Direction.EAST)
        4 -> listOf(Direction.NORTH, Direction.EAST, Direction.SOUTH)
        5 -> listOf(Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST)
        else -> throw IllegalArgumentException()
    }

    fun rotatedDirectionsList(): List<List<Direction>> {
        val rotatedDirectionsList = mutableSetOf(directions)
        var rotatedDirections = directions
        val rotateCount = when (id) {
            1, 3, 4 -> 4
            2 -> 2
            5 -> 1
            else -> throw IllegalArgumentException()
        }
        for (i in 1 until rotateCount) {
            rotatedDirections = rotatedDirection(rotatedDirections)
            rotatedDirectionsList.add(rotatedDirections)
        }

        return rotatedDirectionsList.toList()
    }

    private fun rotatedDirection(direction: List<Direction>): List<Direction> = direction.map(Direction::next)
}

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = readInt()
    val m = readInt()
    val map = List(n) { IntArray(m) }
    val cctvList = mutableListOf<CCTV>()
    repeat(n) { r ->
        repeat(m) { c ->
            val id = readInt()
            if (id in 1..5) {
                cctvList.add(CCTV(r, c, id))
            }
            map[r][c] = if(id == 6) WALL else id
        }
    }

    minBlindSpot(map, cctvList).let(::println)
}

fun minBlindSpot(map: List<IntArray>, cctvList: List<CCTV>): Int {
    fun isValid(r: Int, c: Int): Boolean = r >= 0 && c >= 0 && r < map.size && c < map[0].size
    fun setMap(r: Int, c: Int, direction: Direction, weight: Int) {
        var nextR = r + direction.dr
        var nextC = c + direction.dc
        while (isValid(nextR, nextC) && map[nextR][nextC] != WALL) {
            map[nextR][nextC] += weight
            nextR += direction.dr
            nextC += direction.dc
        }
    }

    fun setMap(r: Int, c: Int, directions: List<Direction>, weight: Int = 1) {
        for (direction in directions) {
            setMap(r, c, direction, weight)
        }
    }

    fun blindSpot(): Int = map.asSequence().flatMap { arr -> arr.filter { it == 0 } }.count()
    fun minBlindSpot(idx: Int): Int {
        if (idx == cctvList.size) return blindSpot()
        val cctv = cctvList[idx]
        var minBlindSpot = Int.MAX_VALUE
        for (directions in cctv.rotatedDirectionsList()) {
            setMap(cctv.r, cctv.c, directions, weight = 1)
            minBlindSpot = min(minBlindSpot, minBlindSpot(idx + 1))
            setMap(cctv.r, cctv.c, directions, weight = -1)
        }

        return minBlindSpot
    }

    return minBlindSpot(0)
}


