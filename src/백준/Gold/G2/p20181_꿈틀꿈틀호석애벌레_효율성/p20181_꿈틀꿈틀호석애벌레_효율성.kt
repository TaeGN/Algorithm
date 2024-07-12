package 백준.Gold.G2.p20181_꿈틀꿈틀호석애벌레_효율성

import java.io.StreamTokenizer
import kotlin.math.max

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = nextInt()
    val M = nextInt()
    val sumArr = IntArray(N)
    var sum = 0
    repeat(N) { idx ->
        sumArr[idx] = (sum + nextInt()).also { sum = it }
    }

    fun IntArray.binarySearch(startIdx: Int = 0, endIdx: Int): Int {
        val mid = (startIdx + endIdx) / 2
        println("$startIdx $endIdx")
        if (startIdx == mid) return if (this[endIdx] - getOrElse(mid - 1) { 0 } >= M) mid + 1 else mid
        return if (this[endIdx] - getOrElse(mid - 1) { 0 } >= M) binarySearch(mid + 1, endIdx)
        else binarySearch(startIdx, mid)
    }

    val dp = IntArray(N)
    for (endIdx in 0 until N) {
        val startIdx = sumArr.binarySearch(endIdx = max(0, endIdx - 1))
//        println("$startIdx $endIdx")
        dp[endIdx] = max(dp.getOrElse(endIdx - 1) { 0 },
            dp.getOrElse(startIdx - 1) { 0 } + max(0, sumArr[endIdx] - sumArr.getOrElse(startIdx - 1) { 0 } - M))
    }
    println(dp[N - 1])
//    println(sumArr.contentToString())
//    println(dp.contentToString())
}