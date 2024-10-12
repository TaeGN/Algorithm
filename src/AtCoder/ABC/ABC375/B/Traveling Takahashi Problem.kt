package AtCoder.ABC.ABC375.B

import kotlin.math.abs
import kotlin.math.sqrt

fun main() {
    fun dist(x1: Int, y1: Int, x2: Int, y2: Int) =
        sqrt((abs(x1 - x2).let { it.toLong() * it } + abs(y1 - y2).let { it.toLong() * it }).toDouble())

    var px = 0
    var py = 0
    var result = 0.0
    repeat(readln().toInt()) {
        val (x, y) = readln().trim().split(" ").map(String::toInt)
        result += dist(px, py, x, y)
        px = x
        py = y
    }
    result += dist(px, py, 0, 0)
    println(result)
}