package Codeforces.Div4.Round964.F

import java.io.StreamTokenizer
import kotlin.math.max
import kotlin.math.min

const val MOD = 1000000007
const val MAX_N = 200000

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val factorialArr = IntArray(MAX_N + 1).apply { this[0] = 1 }
    for (i in 1..MAX_N) {
        factorialArr[i] = (factorialArr[i - 1].toLong() * i % MOD).toInt()
    }

    fun Int.exponent(n: Int): Int {
        if (n == 0) return 1
        if (n == 1) return this
        val result = (this.toLong() * this % MOD).toInt().exponent(n / 2)
        return if (n % 2 == 1) (this.toLong() * result % MOD).toInt()
        else result
    }

    fun Int.combination(k: Int): Int =
        if (this == 0 || k == 0) 1
        else (factorialArr[this].toLong() *
                ((factorialArr[k].toLong() * factorialArr[this - k] % MOD).toInt().exponent(MOD - 2)) % MOD).toInt()

    val sb = StringBuilder()
    val T = nextInt()
    repeat(T) {
        val n = nextInt()
        val k = nextInt()
        var oneCount = 0
        repeat(n) {
            if (nextInt() == 1) oneCount++
        }
        val zeroCount = n - oneCount
        sb.appendLine(
            if (oneCount < (k + 1) / 2) 0
            else ((max(k - zeroCount, (k + 1) / 2))..min(oneCount, k)).fold(0L) { acc, i ->
                (acc + oneCount.combination(i).toLong() * (n - oneCount).combination(k - i) % MOD) % MOD
            }
        )
    }
    println(sb)
}