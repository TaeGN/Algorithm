package 백준.Gold.G2.p9527_1의개수세기

import kotlin.math.pow

fun main() {
    val binaryCounts = LongArray(59)
    binaryCounts[0] = 1
    for (i in 1 until binaryCounts.size) {
        binaryCounts[i] = binaryCounts[i - 1] * 2 + 2.0.pow(i - 1).toLong() - 1
    }

    fun Long.binaryCount(): Long {
        val binaryNum = toString(2)
        var count = 0L
        var oneCount = 0
        for ((idx, c) in binaryNum.withIndex()) {
            if (c == '1') count += binaryCounts[binaryNum.length - 1 - idx] + oneCount++ * 2.0.pow(binaryNum.length - 1 - idx)
                .toLong()
        }
        return count
    }

    val (A, B) = readln().split(" ").let { it[0].toLong() to it[1].toLong() }
    println(B.binaryCount() - (A - 1).binaryCount())
}