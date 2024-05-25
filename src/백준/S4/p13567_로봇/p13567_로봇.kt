package 백준.S4.p13567_로봇

import java.io.BufferedReader
import java.io.InputStreamReader

class Point(val r: Int = 0, val c: Int = 0) {
    operator fun plus(other: Point) = Point(r + other.r, c + other.c)
    operator fun times(time: Int) = Point(r * time, c * time)
}

enum class Direction(val point: Point) {
    N(Point(-1, 0)),
    E(Point(0, 1)),
    S(Point(1, 0)),
    W(Point(0, -1));

    fun rotateCW90() = Direction.entries[(this.ordinal + 1) % Direction.entries.size]
    fun rotateCCW90() = Direction.entries[(this.ordinal + Direction.entries.size - 1) % Direction.entries.size]
}

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (m, n) = readLine().split(" ").map(String::toInt)
    var point = Point()
    var direction = Direction.E
    fun Point.isValid(size: Int) = r >= 0 && c >= 0 && r < size && c < size
    fun Point.isNotValid(size: Int) = !isValid(size)
    fun endPoint(): String {
        repeat(n) {
            val (key, value) = readLine().split(" ").let { it[0] to it[1].toInt() }
            when (key) {
                "MOVE" -> point += direction.point * value
                "TURN" -> direction = if (value == 0) direction.rotateCW90() else direction.rotateCCW90()
            }
            if (point.isNotValid(m)) return "-1"
        }
        return "${point.c} ${point.r}"
    }
    println(endPoint())
}