package 백준.Gold.G2.p19236_청소년상어

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

enum class Direction(val dr: Int, val dc: Int) {
    N(-1, 0),
    NW(-1, -1),
    W(0, -1),
    SW(1, -1),
    S(1, 0),
    SE(-1, 1),
    E(0, 1),
    NE(-1, 1);

    fun rotateCCW45() = Direction.entries[(this.ordinal + 1) % Direction.entries.size]
}

class Point(val r: Int, val c: Int)
sealed class Pisces
data class Fish(val fishId: Int, var point: Point, var direction: Direction): Pisces() {
    var isAlive = true
    fun swapPoint(other: Fish) {
        val curPoint = point
        point = other.point
        other.point = curPoint
    }
}
data object Empty : Pisces()

data class Shark(var point: Point, var direction: Direction)

const val MAP_SIZE = 4
const val EMPTY = 0
fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val map = List(MAP_SIZE) { MutableList(MAP_SIZE) { EMPTY } }
    val fishList = mutableListOf(Fish(0, Point(0, 0), Direction.N))
    for (r in 0 until MAP_SIZE) {
        val st = StringTokenizer(readLine())
        for (c in 0 until MAP_SIZE) {
            val point = Point(r, c)
            val fishId = st.nextToken().toInt()
            val direction = (st.nextToken().toInt() - 1).let { Direction.entries[it] }
            val fish = Fish(fishId, point, direction)
            fishList.add(fish)
            map[r][c] = fishId
        }
    }

    fun isValidInMap(r: Int, c: Int) = r >= 0 && c >= 0 && r < MAP_SIZE && c < MAP_SIZE

    fishList.sortBy { it.fishId }
    val firstFish = fishList[map[0][0]]
    map[0][0] = 0
    val shark = Shark(firstFish.point, firstFish.direction)
    fun moveFish() {
        for (fish in fishList) {
            for (i in 0 until Direction.entries.size) {
                val nextR = fish.point.r + fish.direction.dr
                val nextC = fish.point.c + fish.direction.dc
                if (isValidInMap(nextR, nextC) && map[nextR][nextC] != EMPTY) {
                    fish.swapPoint(fishList[map[nextR][nextC]])
                    break
                }
            }
        }
    }

    fun moveShark() {
        for (w in 1..3) {
            shark
        }
    }

    fun move() {
        var maxValue = 0

    }
    while (true) {
        moveFish()
        moveShark()
    }
}