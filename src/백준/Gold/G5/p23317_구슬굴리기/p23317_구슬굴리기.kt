package 백준.Gold.G5.p23317_구슬굴리기

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = nextInt()
    val dp = Array(N) { i -> IntArray(i + 1) }.apply { this[0][0] = 1 }
    for (i in 1 until N) {
        for (j in 0..i) {
            dp[i][j] = dp[i - 1].getOrElse(j - 1) { 0 } + dp[i - 1].getOrElse(j) { 0 }
        }
    }
    val M = nextInt()
    val mArr = mutableListOf<Pair<Int, Int>>()
    repeat(M) { mArr.add(nextInt() to nextInt()) }
    mArr.sortBy { it.first }
    var result = 1L
    var r = 0
    var c = 0
    for ((nr, nc) in mArr) {
        result *= (dp.getOrNull(nr - r)?.getOrNull(nc - c) ?: 0)
        r = nr
        c = nc
    }
    val nr = N - 1
    var count = 0L
    for (nc in c until N) {
        count += (dp.getOrNull(nr - r)?.getOrNull(nc - c) ?: 0)
    }
    result *= count
    println(result)
}