package AtCoder.ProblemList.Difficulty800_1199.AABCC

import kotlin.math.sqrt

fun main() {
    val N = readln().trim().toLong()
    val isNotPrime = BooleanArray(sqrt(N.toDouble() / 12).toInt() + 1).apply { this[0] = true; this[1] = true }
    val primeList = mutableListOf<Int>()
    for (i in 2 until isNotPrime.size) {
        if (isNotPrime[i]) continue
        primeList.add(i)
        if (i.toLong() * i >= isNotPrime.size) continue
        for (j in (i * i) until isNotPrime.size step i) {
            isNotPrime[j] = true
        }
    }
    val primeSquareList = primeList.map { it.toLong() * it }
    var result = 0L
    for (i in 0 until (primeList.size - 2)) {
        val aa = primeSquareList[i]
        if (primeSquareList[i] * primeList[i + 1] * primeSquareList[i + 2] > N) break
        for (j in (i + 1) until (primeList.size - 1)) {
            val b = primeList[j]
            val maxK = primeSquareList.binarySearch(N / (aa * b)).let { if (it >= 0) it else -it - 2 }
            if (maxK <= j) break
            result += maxK - j
        }
    }
    println(result)
}