package AtCoder.ProblemList.Difficulty400_799.MinimizeAbs2

import kotlin.math.abs

const val IMPOSSIBLE = Long.MAX_VALUE shr 2
fun main() {
    val D = readln().trim().toLong()
    val square = mutableListOf<Long>()
    var i = 0L
    while (i * i <= D) {
        square.add(i * i)
        i++
    }
    square.add(i * i)

    var result = IMPOSSIBLE
    for (X in square) {
        val idx = square.binarySearch(D - X).let { if (it >= 0) it else -it - 1 }
        result = minOf(result, abs(X + square[idx] - D), abs(X + square.getOrElse(idx - 1) { 0 } - D))
    }
    println(result)
}