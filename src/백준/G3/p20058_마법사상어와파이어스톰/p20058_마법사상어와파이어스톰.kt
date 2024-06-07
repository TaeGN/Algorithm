package 백준.G3.p20058_마법사상어와파이어스톰

import java.io.StreamTokenizer
import kotlin.math.max

enum class Direction(val dr: Int, val dc: Int) {
    SOUTH(1, 0),
    EAST(0, 1),
    NORTH(-1, 0),
    WEST(0, -1)
}

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = readInt()
    val q = readInt()
    val mapSize = 1 shl n
    val map = List(mapSize) { IntArray(mapSize) }

    fun List<IntArray>.isValid(r: Int, c: Int) = try {
        this[r][c]
        true
    } catch (e: IndexOutOfBoundsException) {
        false
    }

    fun List<IntArray>.log(msg: String = "") {
        val sb = StringBuilder("$msg\n")
        for (row in map) {
            for (elm in row) {
                sb.append("$elm ")
            }
            sb.appendLine()
        }
        println(sb)
    }

    repeat(mapSize) { r ->
        repeat(mapSize) { c ->
            map[r][c] = readInt()
        }
    }

    fun rotateCW90(startR: Int, startC: Int, subMapSize: Int) {
        if (subMapSize <= 1) return

        repeat(subMapSize - 1) { i ->
            var dr = 0
            var dc = i
            val startValue = map[startR + dr][startC + dc]
            repeat(3) {
                map[startR + dr][startC + dc] = map[startR + subMapSize - 1 - dc][startC + dr]
                val temp = dr
                dr = subMapSize - 1 - dc
                dc = temp
            }

            map[startR + dr][startC + dc] = startValue
        }
        if (subMapSize > 2) rotateCW90(startR + 1, startC + 1, subMapSize - 2)
    }

    fun isMelt(r: Int, c: Int): Boolean {
        var aroundIceCount = 0
        for (d in Direction.entries) {
            val nextR = r + d.dr
            val nextC = c + d.dc
            if (map.isValid(nextR, nextC) && map[nextR][nextC] > 0) {
                aroundIceCount++
            }
        }

        return aroundIceCount < 3
    }

    fun isIce(r: Int, c: Int): Boolean = map[r][c] > 0
    fun meltIce() {
        val meltList = mutableListOf<IntArray>()
        for (r in 0 until mapSize) {
            for (c in 0 until mapSize) {
                if (isIce(r, c) && isMelt(r, c)) {
                    meltList.add(intArrayOf(r, c))
                }
            }
        }
        meltList.forEach { map[it[0]][it[1]]-- }
    }

    fun totalIceCount(): Int = map.fold(0) { acc, arr -> acc + arr.sum() }
    fun biggestIceBlockCount(): Int {
        var biggestIceBlockCount = 0
        val visited = map.map { row -> row.map { it == 0 }.toMutableList() }
        fun isNotVisited(r: Int, c: Int): Boolean = !visited[r][c]
        fun iceBlockCount(r: Int, c: Int): Int {
            visited[r][c] = true
            var iceBlockCount = 1
            for (d in Direction.entries) {
                val nextR = r + d.dr
                val nextC = c + d.dc
                if (map.isValid(nextR, nextC) && isNotVisited(nextR, nextC)) {
                    iceBlockCount += iceBlockCount(nextR, nextC)
                }
            }

            return iceBlockCount
        }

        for (r in 0 until mapSize) {
            for (c in 0 until mapSize) {
                if (isIce(r, c) && isNotVisited(r, c)) {
                    biggestIceBlockCount = max(biggestIceBlockCount, iceBlockCount(r, c))
                }
            }
        }

        return biggestIceBlockCount
    }

    repeat(q) {
        val l = readInt()
        val subMapSize = 1 shl l
        for (r in 0 until (mapSize / subMapSize)) {
            for (c in 0 until (mapSize / subMapSize)) {
                rotateCW90(r * subMapSize, c * subMapSize, subMapSize)
            }
        }
        meltIce()
    }

    println(totalIceCount())
    println(biggestIceBlockCount())
}