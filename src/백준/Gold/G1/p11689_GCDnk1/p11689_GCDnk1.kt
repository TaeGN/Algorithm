package 백준.Gold.G1.p11689_GCDnk1

import kotlin.math.pow
import kotlin.math.sqrt


fun main() {
    fun Int.primeList(): List<Int> {
        val isPrimeArr = BooleanArray(this + 1) { it >= 2 }
        for (i in 2..this) {
            for (j in (i * 2)..this step i) {
                isPrimeArr[j] = false
            }
        }
        return isPrimeArr.asSequence().mapIndexed { index, b -> if (b) index else 0 }.filter { it > 0 }.toList()
    }

    fun Long.gcd1Count(): Long {
        val primeList = sqrt(this.toDouble()).toInt().primeList()
        var result = 1L
        var num = this
        for (prime in primeList) {
            var count = 0
            while (num % prime == 0L) {
                count++
                num /= prime
            }
            if (count > 0) result *= prime.toDouble().pow(count - 1).toLong() * (prime - 1)
        }

        return if (num > 1) result * (num - 1) else result
    }

    val n = readln().toLong()
    println(n.gcd1Count())
}