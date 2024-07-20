package 백준.Gold.G3.p17235_GoodPizzaGreatPizza

import java.io.StreamTokenizer
import kotlin.math.max
import kotlin.math.min

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = nextInt()
    var minSum = Long.MAX_VALUE
    var maxSum = Long.MIN_VALUE
    var minDiff = Long.MAX_VALUE
    var maxDiff = Long.MIN_VALUE
    repeat(N) {
        val x = nextInt()
        val y = nextInt()
        val sum = x.toLong() + y
        val diff = y.toLong() - x
        minSum = min(minSum, sum)
        maxSum = max(maxSum, sum)
        minDiff = min(minDiff, diff)
        maxDiff = max(maxDiff, diff)
    }

    val minDiagonal = max(maxSum - minSum, maxDiff - minDiff)
    val result = if (minDiagonal % 2 == 0L) "${minDiagonal / 2 * minDiagonal}"
    else (minDiagonal - 1).let { "${it / 2 * it + it}.5" }
    println(result)
}