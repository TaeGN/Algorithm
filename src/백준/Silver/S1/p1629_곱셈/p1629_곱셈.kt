package 백준.Silver.S1.p1629_곱셈

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val A = readInt()
    val B = readInt()
    val MOD = readInt()
    val powerMap = mutableMapOf(1 to A)
    infix fun Int.power(B: Int): Int {
        if (B == 0) return 1
        if (B == 1) return this % MOD
        if (powerMap[B / 2] == null) powerMap[B / 2] = this.power(B / 2)
        val half: Int = powerMap[B / 2]!!
        return if (B % 2 == 1) ((this.toLong() * half % MOD) * half % MOD).toInt()
        else (half.toLong() * half % MOD).toInt()
    }

    println(A power B)
}
