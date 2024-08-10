package 백준.Silver.S2.p11055_가장큰증가하는부분수열

import java.io.StreamTokenizer
import kotlin.math.max

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = nextInt()
    val dp = mutableMapOf(0 to 0)
    val new = mutableMapOf<Int, Int>()
    repeat(N) {
        val num = nextInt()
        for ((maxNum, sum) in dp) {
            if (num > maxNum) new.compute(num) { _, v -> if (v == null) sum + num else max(v, sum + num) }
        }
        for ((maxNum, sum) in new) {
            dp.compute(maxNum) { _, v -> if (v == null) sum else max(v, sum) }
        }
        new.clear()
    }
    println(dp.values.max())
}