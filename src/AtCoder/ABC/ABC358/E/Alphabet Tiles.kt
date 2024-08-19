package AtCoder.ABC.ABC358.E

import kotlin.math.min

const val MOD = 998244353

fun main() {
    fun Int.pow(n: Int): Int {
        if (n == 0) return 1
        if (n == 1) return this
        val halfPow = pow(n / 2)
        return if (n % 2 == 0) (halfPow.toLong() * halfPow % MOD).toInt()
        else ((this.toLong() * halfPow % MOD) * halfPow % MOD).toInt()
    }

    val K = readln().toInt()
    val cArr = ("0 " + readln()).split(" ").map(String::toInt).toIntArray()
    val dp = Array(27) { IntArray(K + 1).apply { this[0] = 1 } }
    val factorial = IntArray(K + 1).apply { this[0] = 1 }
    for (i in 1..K) {
        factorial[i] = (factorial[i - 1].toLong() * i % MOD).toInt()
    }
    val factorialPow = factorial.map { it.pow(MOD - 2) }
    fun Int.combination(n: Int) =
        ((factorial[this].toLong() * factorialPow[this - n] % MOD) * factorialPow[n] % MOD).toInt()

    for (i in 1..26) {
        for (j in 1..K) {
            for (k in 0..min(j, cArr[i])) {
                dp[i][j] = ((dp[i][j] + dp[i - 1][j - k].toLong() * j.combination(k) % MOD) % MOD).toInt()
            }
        }
    }
    var result = 0
    for (j in 1..K) {
        result = (result + dp[26][j]) % MOD
    }
    println(result)
}