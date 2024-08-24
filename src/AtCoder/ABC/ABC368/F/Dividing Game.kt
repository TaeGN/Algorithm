package AtCoder.ABC.ABC368.F

import kotlin.math.sqrt

fun main() {
    val primeList = mutableListOf<Int>()
    val isNotPrime = BooleanArray(100001)
    val sqrt = sqrt(100000.0).toInt()
    for (i in 2..100000) {
        if (isNotPrime[i]) continue
        primeList.add(i)
        if (i > sqrt) continue
        for (j in i * i..100000 step i) {
            isNotPrime[j] = true
        }
    }
    val dArr = IntArray(100001).apply { this[1] = 0 }
    for (i in 2..100000) {
        var count = 0
        for (prime in primeList) {
            if (i < prime) break
            if (i % prime == 0) {
                var remainedI = i
                while (remainedI % prime == 0) {
                    count++
                    remainedI /= prime
                }
            }

        }
        dArr[i] = count
    }
    val N = readln()
    val aArr = readln().split(" ").map(String::toInt)
    println(if (aArr.fold(0) { acc, i -> acc xor dArr[i] } != 0) "Anna" else "Bruno")
}