package 엘리스코드챌린지.떠먹여주는알고리즘.소수출력

import kotlin.math.max

fun main() {
    val N = readln().toInt()
    fun result(): Int = when (N) {
        1 -> 2
        2 -> 3
        else -> {
            val isNotPrimeArr = BooleanArray(N)
            var maxValue = 0
            for (primeNum in 2 until N) {
                if (isNotPrimeArr[primeNum]) continue
                maxValue = max(maxValue, primeNum)
                for (num in (primeNum * 2) until N step primeNum) {
                    isNotPrimeArr[num] = true
                }
            }
            maxValue
        }
    }

    println(result())
}