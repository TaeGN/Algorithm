package 백준.Gold.G4.p7588_Amicable

import java.io.StreamTokenizer
import kotlin.math.sqrt

const val MAX_N = 1_000_000
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val primeList = mutableSetOf<Int>()
    val sumArr = IntArray(MAX_N + 1) { 1 }
    val maxNum = sqrt(MAX_N.toDouble()).toInt()
//    모든 소수에서 진행
    for (num in 2..maxNum) {
        if (sumArr[num] > 1) continue
//        소수의 거듭제곱
        for (i in 1..(maxNum / num)) {
            val numA = num * i
            if (primeList.any { i % it == 0 }) continue
            for (numB in numA..(MAX_N / numA)) {
                val mul = numA * numB
                if (numA == numB) sumArr[mul] += numA
                else sumArr[mul] += numA + numB
            }
        }
        primeList.add(num)
    }

    val resultList = mutableListOf<Pair<Int, Int>>()
    for (numA in 2..MAX_N) {
        val numB = sumArr[numA]
        if (numA < numB && numA == sumArr.getOrElse(numB) { 0 }) resultList.add(numA to numB)
    }

    val sb = StringBuilder()
    var n = 0
    while (readInt().also { n = it } > 0) {
        sb.appendLine("\nAmicable numbers between 1 and $n")
        if (resultList.first().first > n) sb.appendLine("None")
        for ((numA, numB) in resultList) {
            if (numA > n) break
            sb.appendLine("$numA $numB")
        }
    }

    println(sb)
}