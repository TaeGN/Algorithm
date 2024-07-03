package 백준.Silver.S2.p15979_스승님찾기

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

fun main() {
    val (M, N) = readln().split(" ").map { abs(it.toInt()) }.let { it[0] to it[1] }
    fun gcd(a: Int, b: Int): Int {
        require(a >= b)
        return if (b == 0) a
        else gcd(b, a % b)
    }

    println(min(gcd(max(M, N), min(M, N)), 2))
}