package 백준.Gold.G4.p13172_시그마

import java.io.StreamTokenizer

const val MOD = 1_000_000_007

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    infix fun Int.power(n: Int): Int {
        if (n == 0) return 1
        if (n == 1) return this
        val halfPower = this.power(n / 2)
        return if (n % 2 == 1) ((this.toLong() * halfPower % MOD) * halfPower % MOD).toInt()
        else (halfPower.toLong() * halfPower % MOD).toInt()
    }

    fun expectedValueOfDice(n: Int, s: Int) = (s.toLong() * (n power (MOD - 2))) % MOD

    val m = readInt()
    var result = 0
    repeat(m) {
        val n = readInt()
        val s = readInt()
        result = ((result.toLong() + expectedValueOfDice(n, s)) % MOD).toInt()
    }

    println(result)
}