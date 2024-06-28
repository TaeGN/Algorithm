package 백준.Silver.S2.p17359_전구길만걷자

import kotlin.math.abs

fun main() = with(System.`in`.bufferedReader()) {
    fun String.stateChangeCount(): Int {
        var count = 0
        var prev = first()
        for (c in this) {
            if (prev != c) count++
            prev = c
        }
        return count
    }

    val N = readLine().toInt()
    val arr = List(2) { intArrayOf(0, 0) } // 00,01,10,11
    var totalCount = 0
    repeat(N) {
        val state = readLine()
        totalCount += state.stateChangeCount()
        arr[state.first().digitToInt()][state.last().digitToInt()]++
    }

    val diff01And10 = abs(arr[0][1] - arr[1][0])
    if (diff01And10 > 1) totalCount += diff01And10 - 1
    if (arr[0][1] == 0 && arr[1][0] == 0 && arr[0][0] * arr[1][1] > 0) totalCount++
    println(totalCount)
}