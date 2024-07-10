package 백준.Silver.S4.p3036_링

import java.io.StreamTokenizer
import kotlin.math.max
import kotlin.math.min

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    fun Int.gcd(other: Int): Int = if (this == 0 || other == 0) max(this, other)
    else min(this, other).gcd(max(this, other) % min(this, other))

    val sb = StringBuilder()
    val N = nextInt()
    val firstNum = nextInt()
    repeat(N - 1) {
        val num = nextInt()
        val gcd = firstNum.gcd(num)
        sb.appendLine("${firstNum / gcd}/${num / gcd}")
    }
    println(sb)
}