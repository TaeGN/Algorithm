package 백준.Silver.S1.p16207_롤케이크

import java.io.StreamTokenizer
import kotlin.math.min

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = readInt()
    val M = readInt()
    val arr = IntArray(N)
    repeat(N) { idx ->
        arr[idx] = readInt()
    }

    var totalCutCount = 0
    min(arr.sumOf { it / 10 },
        M + arr.asSequence()
            .filter { it % 10 == 0 }
            .map { it / 10 - 1 }
            .sorted()
            .count { curCount -> M >= (totalCutCount + curCount).also { totalCutCount = it } })
        .let(::println)
}