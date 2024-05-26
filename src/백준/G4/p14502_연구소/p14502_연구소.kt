package 백준.G4.p14502_연구소

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer
import kotlin.math.max

data class Point(val r: Int, val c: Int)

enum class State {
    EMPTY, WALL, VIRUS
}

enum class Direction(val dr: Int, val dc: Int) {
    NORTH(-1, 0),
    EAST(0, 1),
    SOUTH(1, 0),
    WEST(0, -1)
}

const val BUILD_WALL_COUNT = 3

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    var st = StringTokenizer(readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()
    val originalMap = List(n) { MutableList(m) { State.EMPTY } }
    val pointMap = List(n) { r -> List(m) { c -> Point(r, c) } }
    operator fun Point.plus(other: Point) = pointMap[r + other.r][c + other.c]
    val emptyList = mutableListOf<Point>()
    val virusList = mutableListOf<Point>()

    for (r in 0 until n) {
        st = StringTokenizer(readLine())
        for (c in 0 until m) {
            val state = State.entries[st.nextToken().toInt()]
            originalMap[r][c] = state
            when (state) {
                State.EMPTY -> emptyList.add(pointMap[r][c])
                State.VIRUS -> virusList.add(pointMap[r][c])
                else -> {}
            }
        }
    }

    val newMap = List(n) { MutableList(m) { State.EMPTY } }
    fun List<MutableList<State>>.rollback() {
        for ((r, row) in originalMap.withIndex()) {
            for ((c, value) in row.withIndex()) {
                this[r][c] = value
            }
        }
    }

    val queue = ArrayDeque<Point>()
    fun ArrayDeque<Point>.rollback() {
        this.clear()
        this.addAll(virusList)
    }

    fun isValidInMap(r: Int, c: Int) = r >= 0 && c >= 0 && r < n && c < m

    fun bfs(selected: MutableList<Int>): Int {
        var safetyArea = emptyList.size - BUILD_WALL_COUNT
        newMap.rollback()
        queue.rollback()
        for (idx in selected) {
            val point = emptyList[idx]
            newMap[point.r][point.c] = State.WALL
        }

        while (queue.isNotEmpty()) {
            val point = queue.removeFirst()
            for (d in Direction.entries) {
                val nextR = point.r + d.dr
                val nextC = point.c + d.dc
                if (isValidInMap(nextR, nextC) && newMap[nextR][nextC] == State.EMPTY) {
                    newMap[nextR][nextC] = State.VIRUS
                    queue.add(pointMap[nextR][nextC])
                    safetyArea--
                }
            }
        }

        return safetyArea
    }

    fun combination(selected: MutableList<Int> = mutableListOf(), idx: Int = 0): Int {
        if (selected.size == BUILD_WALL_COUNT) {
            return bfs(selected)
        }
        if (idx == emptyList.size) return 0

        selected.add(idx)
        val selectedCount = combination(selected, idx + 1)
        selected.removeLast()
        val noSelectedCount = combination(selected, idx + 1)

        return max(selectedCount, noSelectedCount)
    }

    println(combination())
}