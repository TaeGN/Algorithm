package Codeforces.Div2.EducationalCodeforces.Round169.E

import kotlin.math.sqrt

const val MAX_A = 10000000
fun main() {
    val isNotPrime = BooleanArray(MAX_A + 1).apply { this[0] = true; this[1] = true }
    val primeList = mutableListOf<Int>()
    for (i in 2..sqrt(MAX_A.toDouble()).toInt()) {
        if (isNotPrime[i]) continue
        primeList.add(i)
        for (j in i * i..MAX_A step i) {
            isNotPrime[j] = true
        }
    }
    val G = IntArray(MAX_A + 1).apply { this[1] = 1 }
    var prevPrime = 1
    for (i in 3..MAX_A step 2) {
        G[i] = if (isNotPrime[i]) G[primeList.find { i % it == 0 }!!] else (G[prevPrime] + 1).also { prevPrime = i }
    }
    repeat(readln().toInt()) {
        val n = readln().toInt()
        var result = 0
        for (num in readln().split(" ").map(String::toInt)) {
            result = result xor G[num]
        }
        println(if (result > 0) "Alice" else "Bob")
    }
}