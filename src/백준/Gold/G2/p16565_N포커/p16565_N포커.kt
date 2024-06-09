package 백준.Gold.G2.p16565_N포커

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.min

const val MOD = 10_007
const val MAX_N = 52
const val CARD_TYPE = 13
fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt()
    println(numberOfCases(n))
}

fun numberOfCases(n: Int): Int {
    val value = (1..(n / 4)).fold(0L) { acc, i ->
        acc + plusMinusSign(i) * ((MAX_N - 4 * i) combination (n - 4 * i)) * (CARD_TYPE combination i)
    }
    return (value % MOD).toInt()
}

fun plusMinusSign(i: Int) = if (i % 2 == 1) 1 else -1

infix fun Int.combination(c: Int): Long {
    if (c < 0) return 0
    if (c == 0) return 1
    return (1..min(this - c, c)).fold(1L) { acc, i -> acc * (this - i + 1) / i }
}

