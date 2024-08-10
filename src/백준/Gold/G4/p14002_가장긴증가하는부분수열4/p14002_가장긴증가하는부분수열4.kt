package 백준.Gold.G4.p14002_가장긴증가하는부분수열4

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = nextInt()
    val numArr = IntArray(N)
    repeat(N) { idx ->
        numArr[idx] = nextInt()
    }
    val dp = IntArray(N) { 1 }
    val prevIdxArr = IntArray(N) { it }
    for (next in 0 until N) {
        for (prev in 0 until next) {
            if (numArr[prev] < numArr[next] && dp[next] < dp[prev] + 1) {
                dp[next] = dp[prev] + 1
                prevIdxArr[next] = prev
            }
        }
    }
    var endIdx = 0
    var maxSize = 0
    for ((idx, size) in dp.withIndex()) {
        if (maxSize < size) {
            maxSize = size
            endIdx = idx
        }
    }
    println(maxSize)
    val sb = StringBuilder().append(numArr[endIdx])
    while (endIdx != prevIdxArr[endIdx]) {
        endIdx = prevIdxArr[endIdx]
        sb.insert(0, "${numArr[endIdx]} ")
    }
    println(sb)
}