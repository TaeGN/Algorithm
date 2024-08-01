package 백준.Gold.G5.p24053_알고리즘수업_삽입정렬3

import java.io.StreamTokenizer
import kotlin.math.max

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = nextInt()
    val numIdxMap = mutableMapOf<Int, Int>()
    val aArr = IntArray(N)
    val bArr = IntArray(N)
    repeat(N) { idx ->
        val num = nextInt()
        numIdxMap[num] = idx
        aArr[idx] = num
    }
    repeat(N) { idx ->
        bArr[idx] = nextInt()
    }
    var idx = 0
    var num = 0
    var lastSortedIdx = 0
    while (idx < N && num <= bArr[idx]) {
        lastSortedIdx = max(lastSortedIdx, numIdxMap[bArr[idx]]!!)
        num = bArr[idx++]
    }
    aArr.sort(0, lastSortedIdx + 1)

    fun result(): String {
        if (aArr.contentEquals(bArr)) return "1"
        val curNum = aArr[lastSortedIdx + 1]
        for (i in lastSortedIdx downTo 0) {
            if (aArr[i] > curNum) aArr[i + 1] = aArr[i]
            else {
                aArr[i + 1] = curNum
                break
            }
            if (aArr.contentEquals(bArr)) return "1"
        }
        return "0"
    }
    println(result())
}