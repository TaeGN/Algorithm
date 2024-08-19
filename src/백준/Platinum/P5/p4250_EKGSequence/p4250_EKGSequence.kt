package 백준.Platinum.P5.p4250_EKGSequence

import kotlin.math.sqrt

const val MAX_NUM = 1000000
const val MAX_N = 300000
const val IMPOSSIBLE = Int.MAX_VALUE
fun main() {
    val isNotPrime = BooleanArray(MAX_NUM + 1).apply { this[0] = true; this[0] = true }
    val primeList = mutableListOf<Int>()
    val sqrtNum = sqrt(MAX_NUM.toDouble()).toInt()
    for (i in 2..MAX_NUM) {
        if (isNotPrime[i]) continue
        primeList.add(i)
        if (i > sqrtNum) continue
        for (j in i * i..MAX_NUM step i) {
            isNotPrime[j] = true
        }
    }

    val primeMap = primeList.associateWith { 1 }.toMutableMap().apply { this[2] = 2 }
    val selected = BooleanArray(MAX_NUM + 1).apply { this[1] = true; this[2] = true }
    val EKG = IntArray(MAX_NUM + 1).apply { this[1] = 1; this[2] = 2 }
    val result = IntArray(MAX_N + 1).apply { this[1] = 1; this[2] = 2 }
    fun nextCount(prime: Int, count: Int): Int? {
        var nextCount = count
        while (prime * nextCount <= MAX_NUM && selected[prime * nextCount]) nextCount++
        return if (prime * nextCount <= MAX_NUM) nextCount else null
    }

    var remainedCount = MAX_N - 2
    for (i in 3..MAX_NUM) {
        val prevEKG = EKG[i - 1]
        val sqrtPrevEKG = sqrt(prevEKG.toDouble()).toInt()
        var minValue = if (isNotPrime[prevEKG]) IMPOSSIBLE else {
            primeMap.compute(prevEKG) { _, v -> if (v == null) null else nextCount(prevEKG, primeMap[prevEKG]!!) }
            val count = primeMap[prevEKG]
            if (count == null) IMPOSSIBLE
            else prevEKG * count
        }
        for (prime in primeList) {
            if (sqrtPrevEKG < prime) break
            if (prevEKG % prime == 0) {
                primeMap.compute(prime) { _, v -> if (v == null) null else nextCount(prime, primeMap[prime]!!) }
                val count = primeMap[prime]
                if (count != null && minValue > prime * count) {
                    minValue = prime * count
                }
                val prime2 = prevEKG / prime
                if (isNotPrime[prime2]) continue
                primeMap.compute(prime2) { _, v -> if (v == null) null else nextCount(prime2, primeMap[prime2]!!) }
                val count2 = primeMap[prime2]
                if (count2 != null && minValue > prime2 * count2) {
                    minValue = prime2 * count2
                }
            }
        }
        EKG[i] = minValue
        selected[minValue] = true
        if (minValue <= MAX_N) {
            result[minValue] = i
            if (--remainedCount == 0) break
        }
    }
    val sb = StringBuilder()
    while (true) {
        val n = readln().toInt()
        if (n == 0) break
        sb.appendLine("The number $n appears in location ${result[n]}.")
    }
    println(sb)
}