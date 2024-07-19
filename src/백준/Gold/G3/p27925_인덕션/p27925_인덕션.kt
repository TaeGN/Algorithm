package 백준.Gold.G3.p27925_인덕션

import java.io.StreamTokenizer
import kotlin.math.min

const val IMPOSSIBLE = Int.MAX_VALUE

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = nextInt()
    val prevMinCountArr = IntArray(1000) { IMPOSSIBLE }.apply { this[0] = 0 }
    val nextMinCountArr = IntArray(1000) { IMPOSSIBLE }
    fun Int.diff(other: Int): Int {
        val diff1 = if (this <= other) other - this else (other + 10) - this
        val diff2 = if (this >= other) this - other else (this + 10) - other
        return min(diff1, diff2)
    }
    repeat(N) {
        val nextTemperature = nextInt()
        for (prevNum in prevMinCountArr.indices) {
            if (prevMinCountArr[prevNum] == IMPOSSIBLE) continue
            val first = prevNum / 100
            val second = prevNum / 10 % 10
            val third = prevNum % 10
            var nextNum = nextTemperature * 100 + second * 10 + third
            nextMinCountArr[nextNum] =
                min(nextMinCountArr[nextNum], first.diff(nextTemperature) + prevMinCountArr[prevNum])
            nextNum = first * 100 + nextTemperature * 10 + third
            nextMinCountArr[nextNum] =
                min(nextMinCountArr[nextNum], second.diff(nextTemperature) + prevMinCountArr[prevNum])
            nextNum = first * 100 + second * 10 + nextTemperature
            nextMinCountArr[nextNum] =
                min(nextMinCountArr[nextNum], third.diff(nextTemperature) + prevMinCountArr[prevNum])
        }
        for (i in prevMinCountArr.indices) {
            prevMinCountArr[i] = nextMinCountArr[i]
            nextMinCountArr[i] = IMPOSSIBLE
        }
    }

    println(prevMinCountArr.min())
}