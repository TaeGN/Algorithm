package 백준.G5.p14503_로봇청소기

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

enum class AreaState { BEFORE_CLEANING, WALL, AFTER_CLEANING }
enum class Direction(val dr: Int, val dc: Int) {
    NORTH(-1, 0) {
        override fun rotateCCW() = WEST
    },
    EAST(0, 1){
        override fun rotateCCW() = NORTH
    },
    SOUTH(1, 0){
        override fun rotateCCW() = EAST
    },
    WEST(0, -1){
        override fun rotateCCW() = SOUTH
    };

    abstract fun rotateCCW(): Direction
}

class Position(var r: Int, var c: Int, var d: Direction) {
    fun rotateCCW() {
        d = d.rotateCCW()
    }
    fun goBack() {
        r -= d.dr
        c -= d.dc
    }
    fun goStraight() {
        r += d.dr
        c += d.dc
    }
}

class RoboticVacuum(private val position: Position, private val area: List<MutableList<AreaState>>) {
    private var cleaningCount = 0
    fun cleaning(): Int {
        while (true) {
            cleaningCurPosition()
            if(isCleanedAll()) {
                if (canGoBack()) {
                    position.goBack()
                } else {
                    return cleaningCount
                }
            } else {
                position.rotateCCW()
                if(canGoStraight()) {
                    position.goStraight()
                }
            }
        }
    }

    private fun cleaningCurPosition() {
        if (area[position.r][position.c] == AreaState.AFTER_CLEANING) return
        area[position.r][position.c] = AreaState.AFTER_CLEANING
        cleaningCount++
    }

    private fun isCleanedAll(): Boolean {
        for (d in Direction.entries) {
            val nextR = position.r + d.dr
            val nextC = position.c + d.dc
            if (isValidInArea(nextR, nextC) && isBeforeCleaning(nextR, nextC)) return false
        }

        return true
    }
    private fun isBeforeCleaning(r: Int, c: Int): Boolean = area[r][c] == AreaState.BEFORE_CLEANING
    private fun isValidInArea(r: Int, c: Int): Boolean = r >= 0 && c >= 0 && r < area.size && c < area[0].size
    private fun canGoBack() : Boolean {
        val nextR = position.r - position.d.dr
        val nextC = position.c - position.d.dc
        return isValidInArea(nextR, nextC) && isNotWall(nextR, nextC)
    }
    private fun isNotWall(r: Int, c: Int): Boolean = !isWall(r, c)
    private fun isWall(r: Int, c: Int): Boolean = area[r][c] == AreaState.WALL
    private fun canGoStraight() : Boolean {
        val nextR = position.r + position.d.dr
        val nextC = position.c + position.d.dc
        return isValidInArea(nextR, nextC) && isBeforeCleaning(nextR, nextC)
    }
}

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    var st = StringTokenizer(readLine())
    val areaRowSize = st.nextToken().toInt()
    val areaColSize = st.nextToken().toInt()

    st = StringTokenizer(readLine())
    val position =
        Position(st.nextToken().toInt(), st.nextToken().toInt(), st.nextToken().toInt().let { Direction.entries[it] })
    val area = List(areaRowSize) { MutableList(areaColSize) { AreaState.BEFORE_CLEANING } }

    for (r in 0 until areaRowSize) {
        st = StringTokenizer(readLine())
        for (c in 0 until areaColSize) {
            area[r][c] = AreaState.entries[st.nextToken().toInt()]
        }
    }

    val roboticVacuum = RoboticVacuum(position, area)
    println(roboticVacuum.cleaning())
}