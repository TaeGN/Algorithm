package 백준.Gold.G1.p17420_깊콘이넘쳐흘러

import java.util.*

fun main() {
    val N = readln().toInt()
    val A = readln().split(" ").map(String::toInt)
    val B = readln().split(" ").map(String::toInt)
    val group = TreeMap<Int, MutableList<Int>>()
    for (i in 0 until N) {
        group.compute(B[i]) { _, v -> v?.also { it.add(A[i]) } ?: mutableListOf(A[i]) }
    }
    var totalCount = 0L
    var prevA = 0L
    for ((curB, curAList) in group) {
        var maxCurA = 0L
        for (curA in curAList) {
            val min = maxOf(prevA, curB.toLong())
            if (curA < min) {
                val count = (min - curA - 1) / 30 + 1
                totalCount += count
                maxCurA = maxOf(maxCurA, curA + count * 30)
            } else maxCurA = maxOf(maxCurA, curA.toLong())
        }
        prevA = maxOf(prevA, maxCurA)
    }
    println(totalCount)
}